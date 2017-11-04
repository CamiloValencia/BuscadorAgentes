/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1;

/**
 * Clase de intercambio de termino de busqueda
 * 
 * @author k_mil
 */
public class Busqueda implements java.io.Serializable {
	/**
	 * Identificador serialización
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Termino de busqueda
	 */
	private String termino;
	/**
	 * 1 para terinos, 2 para indices
	 */
	private int tipoBusqueda;
	/**
	 * Resultado de la búsqueda
	 */
	private ResultadoBusqueda resultado;

	/**
	 * Constructor vacio
	 */
	public Busqueda() {

	}

	/**
	 * Constructor por termino
	 * 
	 * @param termino
	 *            Termino a buscar
	 */
	public Busqueda(String termino) {
		this.termino = termino;
	}

	/**
	 * Constructor por termino y tipo de b�squeda
	 * 
	 * @param termino
	 *            Termino a buscar
	 */
	public Busqueda(String termino, int tipoBusqueda) {
		this.termino = termino;
		this.tipoBusqueda = tipoBusqueda;
	}

	public String getTermino() {
		return termino;
	}

	public void setTermino(String termino) {
		this.termino = termino;
	}

	public int getTipoBusqueda() {
		return tipoBusqueda;
	}

	public void setTipoBusqueda(int tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	public ResultadoBusqueda getResultado() {
		return resultado;
	}

	public void setResultado(ResultadoBusqueda resultado) {
		this.resultado = resultado;
	}

}
