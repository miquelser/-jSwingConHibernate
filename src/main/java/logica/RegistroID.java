package logica;

import java.io.Serializable;

//No es una entidad, debe implementar serializable
public class RegistroID implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String clase;
	private String socio;
	
	public RegistroID() {
		super();
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getSocio() {
		return socio;
	}
	public void setSocio(String socioo) {
		this.socio = socioo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Integer.parseInt(clase);
		result = prime * result + ((socio == null) ? 0 : socio.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroID other = (RegistroID) obj;
		if (clase != other.clase)
			return false;
		if (socio == null) {
			if (other.socio != null)
				return false;
		} else if (!socio.equals(other.socio))
			return false;
		return true;
	}
	
	
}

