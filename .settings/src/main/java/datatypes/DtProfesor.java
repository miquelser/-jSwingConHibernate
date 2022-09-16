package datatypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logica.Clase;


public class DtProfesor extends DtUsuario {
	

	private String descripcion;
	private String biografia;
	private String sitioWeb;
	
	private List<DtClase> clases = new ArrayList<>();

	
	
	public DtProfesor(String nickname, String nombre, String apellido, String email, Date fechaNac, String descripcion,
			String biografia, String sitioWeb, List<DtClase> clases) {
		super(nickname, nombre, apellido, email, fechaNac);
		this.descripcion = descripcion;
		this.biografia = biografia;
		this.sitioWeb = sitioWeb;
		this.clases = clases;
	}

	public String getDescripcion() {
		return descripcion;
	} 

	public String getBiografia() {
		return biografia;
	}

	public String getSitioWeb() {
		return sitioWeb;
	} 	

	@Override
	public String toString() {
		return super.toString() + "\nDescripcion = " + descripcion + "\nBiografia = " + biografia +"\nSitioWeb = " +  sitioWeb;
	}

	public List<DtClase> getClases() {
		return clases;
	}
}
