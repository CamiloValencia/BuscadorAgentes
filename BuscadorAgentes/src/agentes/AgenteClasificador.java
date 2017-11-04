/*
 * Copyright (C) 2017 k_mil
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package agentes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import is1.Busqueda;
import is1.Indice;
import is1.ResultadoBusqueda;
import is1.Termino;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class AgenteClasificador extends Agent {

	/**
	 * Serial objeto serializable
	 */
	private static final long serialVersionUID = 2350196823516633522L;
	/**
	 * Objeto manejador de conexi�n con la base de datos
	 */
	private Connection conn = null;

	/**
	 * Inicialización del agente
	 */
	protected void setup() {
		String userName = "kmilov";
		String password = "Qw1209Po.";
		String url = "jdbc:sqlserver://dbo.cuaeycy9b5bz.us-west-2.rds.amazonaws.com:1433;databaseName=BuscadorAgentes";

		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			conn = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Comportamtiento a la espera de realizar una b�squeda
		addBehaviour(new CyclicBehaviour(this) {

			/**
			 * Serial id
			 */
			private static final long serialVersionUID = 1L;

			public void action() {
				ACLMessage msg = receive();
				if (msg != null) {
					Busqueda busqueda;
					try {
						busqueda = (Busqueda) msg.getContentObject();
					} catch (UnreadableException e1) {
						e1.printStackTrace();
						busqueda = null;
					}
					ACLMessage reply = msg.createReply();
					reply.setPerformative(ACLMessage.INFORM);
					try {
						if (busqueda.getTipoBusqueda() == 1) {
							reply.setContentObject(obteterTerminos(conn, busqueda.getTermino()));
						} else if (busqueda.getTipoBusqueda() == 2) {
							reply.setContentObject(obteterIndices(conn, busqueda.getTermino()));
						}
					} catch (IOException e) {
						reply.setContent(null);
						e.printStackTrace();
					} catch (SQLException e) {
						reply.setContent(null);
						e.printStackTrace();
					}
					send(reply);
				} else {
					block();
				}
			}
		});

	}

	/**
	 * Obtiene terminos que concuerdan con la b�squeda
	 * 
	 * @param conn
	 *            Conexión
	 * @param terminoBusqueda
	 * @return
	 * @throws SQLException
	 */
	public ResultadoBusqueda obteterTerminos(Connection conn, String terminoBusqueda) throws SQLException {
		ResultadoBusqueda result = new ResultadoBusqueda();
		String query = "SELECT * FROM terminos where termino like '%" + terminoBusqueda + "%'";

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			int id = rs.getInt("id_termino");
			String termino = rs.getString("termino");
			result.getResultados().add(new Termino(termino, id));
		}
		st.close();
		return result;
	}

	/**
	 * Obtiene indices de un termino
	 * 
	 * @param conn
	 *            Conexi�n
	 * @param terminoBusqueda
	 *            Termino de B�squeda
	 * @return
	 * @throws SQLException
	 */
	public ResultadoBusqueda obteterIndices(Connection conn, String terminoBusqueda) throws SQLException {
		ResultadoBusqueda result = new ResultadoBusqueda();
		String query = "SELECT * FROM indices where id_termino = " + terminoBusqueda +" ORDER BY nivel_confianza desc";

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			int id = rs.getInt("id_termino");
			int conf = rs.getInt("nivel_confianza");
			String desc = rs.getString("descripcion");
			String url = rs.getString("url");
			result.getIndices().add(new Indice(desc,conf, url, new Termino(id)));
		}
		st.close();
		return result;
	}

	protected void takeDown() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		try {
			DFService.deregister(this);
		} catch (Exception e) {
		}
	}

}
