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
package is1;

import java.io.Serializable;

/**
 * Clase para manejo de indices
 * 
 * @author k_mil
 *
 */
public class Indice implements Serializable {
	/**
	 * Id serialización
	 */
	private static final long serialVersionUID = 4021328151967221257L;
	/**
	 * Descripción del repositorio donde se encuentra este recurso
	 */
	private String descripcion;
	/**
	 * Nivel de confianza del recurso
	 */
	private int nivelConfianza;
	/**
	 * Url del recurso
	 */
	private String URL;
	/**
	 * Termino asociado al indice
	 */
	private Termino termino;

	/**
	 * Constructor vacio
	 */
	public Indice() {
	}

	/**
	 * Constructor atributos
	 * 
	 * @param descripcion
	 * @param nivelConfianza
	 * @param uRL
	 * @param termino
	 */
	public Indice(String descripcion, int nivelConfianza, String uRL, Termino termino) {
		super();
		this.descripcion = descripcion;
		this.nivelConfianza = nivelConfianza;
		URL = uRL;
		this.termino = termino;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNivelConfianza() {
		return nivelConfianza;
	}

	public void setNivelConfianza(int nivelConfianza) {
		this.nivelConfianza = nivelConfianza;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Termino getTermino() {
		return termino;
	}

	public void setTermino(Termino termino) {
		this.termino = termino;
	}

}
