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
package ws;

import is1.Busqueda;
import jade.core.Profile;
import jade.util.leap.Properties;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import acciones.Accion;
import acciones.AccionEnviarMensaje;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jade.wrapper.gateway.JadeGateway;

/**
 * Servicio web REST para b�squeda de terminos e indices
 *
 * @author k_mil
 */
@Path("buscar")
public class BuscadorWs {

    @Context
    private UriInfo context;

    /**
     * Constructor donde se inicializa el agente
     */
    public BuscadorWs() {
        Properties pp = new Properties();
        pp.setProperty(Profile.MAIN_HOST, "localhost");
        pp.setProperty(Profile.MAIN_PORT, "1099");
        JadeGateway.init("agentes.AgenteBuscador", pp);
    }

    /**
     * Metodo POST que recibe el termino de búsqueda y devuelve las posibles concordancias
     *
     * @param busqueda termino de busqueda
     * @return Response Respuesta HTTP con los posibles resultados
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(Busqueda busqueda) {
    	busqueda.setTipoBusqueda(1);
        Accion accion = new AccionEnviarMensaje();
        Busqueda rta = accion.enviar(busqueda);
        return Response.ok(rta.getResultado().getResultados()).build();
    }
    
    @GET
	@Path("{id_termino}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerIndices(@PathParam("id_termino") String idTermino) {
        Accion accion = new AccionEnviarMensaje();
	    Busqueda rta = accion.enviar(new Busqueda(idTermino, 2));
	    return Response.ok(rta.getResultado().getIndices()).build();
    }
}
