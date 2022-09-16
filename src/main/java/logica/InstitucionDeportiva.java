package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Query;

import datatypes.DtActDeportiva;
import datatypes.DtClase;
import datatypes.DtInstitucionD;
import datatypes.DtProfesor;
import datatypes.DtUsuario;
import persistencia.Conexion;
@Entity(name = "institucion")
@javax.persistence.Table(name = "institucion")
public class InstitucionDeportiva {
	@Id
	private String nombre;
	private String descripcion;
	private String url;

	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private List<ActividadDeportiva> actividadesD = new ArrayList<>();
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Profesor> profesores = new ArrayList<>();
		
	public InstitucionDeportiva() {
		super();

	}
		
	public InstitucionDeportiva(String nombre, String descripcion, String url) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	//BUSCAR LAS ACTIVIDADES ASOCIADAS EN AL BD Y DEVUELVE UNA LISTA CON SUS NOMBRES

	public String[] getActividadesD() {
		String[] actividades_ret = new String[this.actividadesD.size()+1];
		actividades_ret[0]="Selecccionar";
        int i=1;
        for(ActividadDeportiva AC:this.actividadesD) {
        	actividades_ret[i]=AC.getNombre();
        	i++;
        }
        return actividades_ret;

	}
	
	public void agregarActDeportiva(ActividadDeportiva aD) {
		actividadesD.add(aD);
	}
		
	public ActividadDeportiva buscarActividad(String nombreAD) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();	
		ActividadDeportiva actDepor = em.find(ActividadDeportiva.class, nombreAD);
		return actDepor;
	}
	
	//Probando retornos Dt
	
	public DtInstitucionD getDtInstitucion() {
		ArrayList<DtActDeportiva> actD = new ArrayList<DtActDeportiva>();
		ArrayList<DtProfesor> profe = new ArrayList<DtProfesor>();
		
		boolean vaciaA=this.actividadesD.isEmpty();
		boolean vaciaC=this.profesores.isEmpty();
		
		if(vaciaA && vaciaC ) {
				actD=null;
				profe=null;
		} else if(vaciaA){
			actD=null;
			List<Profesor> profesor=profesores;
			
			for (Profesor prof : profesor) {
				DtProfesor p= (DtProfesor) prof.getDtUsuario();
				profe.add(p);
			}
			
		}else if(vaciaC){
			profe=null;
			
			List<ActividadDeportiva> Actividades=actividadesD;
			
			for (ActividadDeportiva actividadDeportiva : Actividades) {
				actD.add(actividadDeportiva.getDtActDeportiva());
			}
		}else {
			List<ActividadDeportiva> Actividades=actividadesD;
			
			for (ActividadDeportiva actividadDeportiva : Actividades) {
				actD.add(actividadDeportiva.getDtActDeportiva());
			}
			
			List<Profesor> profesor=profesores;
			
			for (Profesor prof : profesor) {
				DtProfesor p= (DtProfesor) prof.getDtUsuario();
				profe.add(p);			}
		}
			

		DtInstitucionD dtIntitucion = new DtInstitucionD(this.getNombre(), this.getDescripcion(), this.getUrl(),actD,profe);

		return dtIntitucion;
	}
	

	public List<Profesor> getProfesores() {
		  return profesores;
	}
	 
	public void agregarProfesor(Profesor profe) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
	
		em.getTransaction().begin();	
		em.persist(profe);
		em.getTransaction().commit();
		
		this.profesores.add(profe);
	}

	public boolean existeProfesor(){
		boolean res=false;
		if (!profesores.isEmpty()) {
			for(Profesor a: profesores) {
				if (a.getNombre().equals(nombre)) {
					res=true;
				}
			}
		}
		return res;
	}
	public Profesor getProfesordeIntD(String nombreP) {	
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();	
		Profesor prof = em.find(Profesor.class, nombreP);
		return prof;
	}

	public String[] getProfesoresN() {
		String[] actividades_ret = new String[this.profesores.size()+1];
		actividades_ret[0]="Selecccionar";
        int i=1;
        for(Profesor AC:this.profesores) {
        	actividades_ret[i]=AC.getNickname();
        	i++;
        }
        return actividades_ret;

	}
	
	public List<ActividadDeportiva> getActividadesDep() {
		return this.actividadesD;
	}
	
}
