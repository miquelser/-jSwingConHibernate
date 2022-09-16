package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import datatypes.DtActDeportiva;
import datatypes.DtClase;
import datatypes.DtInstitucionD;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import persistencia.Conexion;
@Entity
public class ActividadDeportiva {
	@Id

	private String nombre;
	private String descripcion;
	private int duracion;
	private float costo;
	private Date fecha;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Clase> clases = new ArrayList<>();
	
	
	public ActividadDeportiva() {
		super();
	}
	
	public ActividadDeportiva(String nombre, String descripcion, int duracion, float costo, Date fecha) {
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public boolean existeClase(String nombre) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		boolean res = false;
		Clase clase = em.find(Clase.class, nombre);
		if (clase != null) {
			res = true;
		}
		return res;
	}

	public List<DtClase> getClases() {
		List<DtClase> C= new ArrayList<>();
		for (Clase Class : clases) {
			C.add(Class.getDtClase());
		}
		
		return C;
	}
	

	public void setClase(Clase clase) {
		this.clases.add(clase);
	}
	
	public DtActDeportiva getDtActDeportiva() {	
		DtActDeportiva newAct= new DtActDeportiva(this.getNombre(), this.getDescripcion(), this.getDuracion(), this.getCosto(),this.getFecha());
		
		return newAct;
	}
	
	

	public Clase getclase(String nombreClase) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		Clase clase = em.find(Clase.class, nombreClase);
		
		return clase;
		
	}
	
	public DtClase getDtclase(String nombreClase) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		Clase clase = em.find(Clase.class, nombreClase);
		
		return clase.getDtClase();
		
	}

	public String[] getNombresClases() {
		String[] clases_ret = new String[this.clases.size()+1];
		clases_ret[0]="Selecccionar";
        int i=1;
        for(Clase C:this.clases) {
        	clases_ret[i]=C.getNombre();
        	i++;
        }
        return clases_ret;
	}

}
