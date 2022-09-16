package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;

import datatypes.DtClase;
import datatypes.DtProfesor;
import datatypes.DtUsuario;
import persistencia.Conexion;

@Entity
@DiscriminatorValue("P")
public class Profesor extends Usuario{
	private String descripcion;
	private String biografia;
	private String sitioWeb;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Clase> clases = new ArrayList<>();
	
	public Profesor() {
		super();
	}
	
	public Profesor(String nickname, String nombre, String apellido, String email, Date fechaNac, String descripcion, String biografia, String sitioWeb) {
		super(nickname, nombre, apellido, email, fechaNac);
		this.descripcion = descripcion;
		this.biografia = biografia;
		this.sitioWeb = sitioWeb;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getSitioWeb() {
		return sitioWeb;
	}

	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}
	
	 public List<Clase> getClases() {
		return clases;
	}

	public void setClase(Clase clase) {
		/*Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();	
		em.persist(clase);
		em.getTransaction().commit();*/
		this.clases.add(clase);
	}

	public List<DtClase> getDtProfesores() {
		List<DtClase> Class= new ArrayList<>();
		
		for (Clase C : clases) {
			Class.add(C.getDtClase());
		}
		return Class;
	} 
	
	@Override
	public DtUsuario getDtUsuario() {
		ArrayList<DtClase> Class = new ArrayList<DtClase>();
		
		if(this.clases.isEmpty()) {
			Class=null;
		}
		else {
			List<Clase> clasee=clases;
		
			for (Clase C : clasee) {
				Class.add(C.getDtClase());
			}
		}
		return new DtProfesor(this.getNickname(), this.getNombre(), this.getApellido(), this.getEmail(), this.getFechaNac(), this.descripcion, this.biografia, this.sitioWeb,Class);
	}
	  
	 
	
}
