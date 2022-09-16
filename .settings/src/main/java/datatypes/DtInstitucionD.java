package datatypes;

import java.util.ArrayList;
import java.util.List;

import logica.ActividadDeportiva;
import logica.Profesor;

public class DtInstitucionD {

	private String nombre;
	private String descripcion;
	private String url;
	private List<DtActDeportiva> actividadesD = new ArrayList<>();
	private List<DtProfesor> profesores = new ArrayList<>();

	
	
	
	public DtInstitucionD(String nombre, String descripcion, String url, List<DtActDeportiva> actividadesD,List<DtProfesor>profesores) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.url=url;
		this.actividadesD=actividadesD;
		this.profesores=profesores;
	}
	
	private List<DtActDeportiva> getActividadesD() {
		return actividadesD;
	}


	private List<DtProfesor> getProfesores() {
		return profesores;
	}

	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getUrl() {	
		return url;
	}

	@Override
	public String toString() {
		return "DtInstitucion [nombre=" + nombre + ", descripcion=" + descripcion + ", url=" + url + ",profesores=" + "]";
	}

}

