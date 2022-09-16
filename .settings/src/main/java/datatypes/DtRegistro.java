package datatypes;

import java.util.Date;

import logica.Clase;
import logica.Socio;

public class DtRegistro {
	private Date fecha;
	
	private DtSocio socio;
	
	private DtClase clase;
	
	
	public DtRegistro(Date fecha) {
		super();
		this.fecha = fecha;
	}


	public Date getFecha() {
		return fecha;
	}


	public DtSocio getSocio() {
		return socio;
	}


	public DtClase getClase() {
		return clase;
	}
	
	
	
	
}
