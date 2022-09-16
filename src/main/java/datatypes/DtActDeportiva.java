package datatypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DtActDeportiva {
	
	private String nombre;
	private String descripcion;
	private int duracion;
	private float costo;
	private Date fecha;
	
	//private List<String> clases=new ArrayList<>();
	
	public DtActDeportiva(String nombre, String descripcion, int duracion, float costo, Date fecha) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costo = costo;
		this.fecha = fecha;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	@Override
	public String toString() {
		return "Nombre = " + nombre + "\nDescripcion = " + descripcion + "\nDuracion = " + duracion +"\nCosto =" + costo + "\nFecha = " + fecha;
	}	

}
