package datatypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logica.ActividadDeportiva;
import logica.Profesor;
import logica.Registro;

public class DtClase {

	private String nombre;
	private Date fecha;
	private Date horaInicio;
	private String url;
	private Date fechaReg;
	
	private List<DtRegistro> registros = new ArrayList<>();
	
	private String profe;
	
	private DtActDeportiva act;
	
	
	

	public DtClase(String nombre, Date fecha, Date horaInicio, String url, Date fechaReg, List<DtRegistro> registros,
			String profe, DtActDeportiva act) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.url = url;
		this.fechaReg = fechaReg;
		this.registros = registros;
		this.profe = profe;
		this.act = act;
	}

	public String getNombre() {
		return nombre;
	}
		
	public Date getFecha() {
		return fecha;
	}
	
	public Date getHoraInicio() {
		return horaInicio;		
	}
	
	public String getUrl() {
		return url;
	}
	
	public Date getFechaReg() {
		return fechaReg;	
	}

	@Override
	public String toString() {
		return "DtClase [nombre=" + nombre + ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", url=" + url
				+ ", fechaReg=" + fechaReg + "]";
	}

	public List<DtRegistro> getRegistros() {
		return registros;
	}

	public DtActDeportiva getAct() {
		return act;
	}

	public String getProfe() {
		return profe;
	}
	
	
	
}
	
