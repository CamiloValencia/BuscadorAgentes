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
 * Clase para manejo de terminos
 * 
 * @author k_mil
 *
 */
public class Termino implements Serializable {
	/**
	 * Id serialización
	 */
	private static final long serialVersionUID = -7764575413989502369L;

	/**
	 * Constructor por defecto
	 */
	public Termino() {
	}

	/**
	 * Constructor por id termino
	 * 
	 * @param idTermino
	 */
	public Termino(int idTermino) {
		this.idTermino = idTermino;
	}

	/**
	 * Constructor con atributos
	 * 
	 * @param termino
	 *            Termino
	 * @param idTermino
	 *            Id del termino en la base de datos
	 */
	public Termino(String termino, int idTermino) {
		super();
		this.termino = termino;
		this.idTermino = idTermino;
	}

	/**
	 * Termino de búsqueda
	 */
	private String termino;
	/**
	 * Id de termino en la base de datos
	 */
	private int idTermino;

	public String getTermino() {
		return termino;
	}

	public void setTermino(String termino) {
		this.termino = termino;
	}

	public int getIdTermino() {
		return idTermino;
	}

	public void setIdTermino(int idTermino) {
		this.idTermino = idTermino;
	}

}
