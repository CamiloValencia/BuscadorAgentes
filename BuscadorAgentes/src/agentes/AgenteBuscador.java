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

import is1.Busqueda;
import is1.ResultadoBusqueda;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.gateway.GatewayAgent;

/**
 * Agente que recibe el mensaje de busqueda y lo envÃ­a al agente clasificador.
 * Para iniciar estos agentes ejecuttar JADE como java -cp
 * "lib/jade.jar;lib/buscadorAgentes.jar;lib/sqljdbc42.jar;lib/commons-codec/commons-codec-1.3.jar"
 * jade.Boot -gui
 *
 * 
 * @author k_mil
 */
public class AgenteBuscador extends GatewayAgent {

	/**
	 * Id serial
	 */
	private static final long serialVersionUID = 170624222873439910L;
	Busqueda busqueda = null;

	/**
	 * MÃ©todo que se ejecuta cuando se invoca JadeGateWay.execute(objeto) en el
	 * servicoi web
	 *
	 * @param obj
	 */
	protected void processCommand(java.lang.Object obj) {
		if (obj instanceof Busqueda) {
			busqueda = (Busqueda) obj;
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(new AID("AgenteClasificador", AID.ISLOCALNAME));
			try {
				msg.setContentObject(busqueda);
			} catch (IOException e) {
				System.out.println("No se puede crear mensaje");
				e.printStackTrace();
			}
			send(msg);
		}
	}

	/**
	 * Inicialización del agente, se aÃ±ade un comportamiento ciclico para esperar la
	 * respuesta del agente clasificador
	 */
	@Override
	public void setup() {
		addBehaviour(new CyclicBehaviour(this) {
			/**
			 * id serialización
			 */
			private static final long serialVersionUID = 230292330434317869L;

			@Override
			public void action() {
				ACLMessage msg = receive();
				if ((msg != null) && (busqueda != null)) {
					ResultadoBusqueda res;
					try {
						res = (ResultadoBusqueda) msg.getContentObject();
					} catch (UnreadableException e) {
						e.printStackTrace();
						res = null;
					}
					busqueda.setResultado(res);
					releaseCommand(busqueda);
				} else {
					block();
				}
			}
		});
		super.setup();
	}

}
