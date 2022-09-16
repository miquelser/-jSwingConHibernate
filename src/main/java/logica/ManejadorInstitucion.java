package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import datatypes.DtClase;
import datatypes.DtInstitucionD;
import datatypes.DtUsuario;
import persistencia.Conexion;


public class ManejadorInstitucion {
	private static ManejadorInstitucion instancia = null;
	
	private List<InstitucionDeportiva> instituciones = new ArrayList<>();
	
	private ManejadorInstitucion(){}
	
	public static ManejadorInstitucion getInstancia() {
		if (instancia == null)
			instancia = new ManejadorInstitucion();
		return instancia;
	}

	public void agregarInstitucion(InstitucionDeportiva inst) {	
		instituciones.add(inst);
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		em.getTransaction().begin();	
		em.persist(inst);
		em.getTransaction().commit();
	}
	
	public InstitucionDeportiva buscarInstitucion(String inst) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		InstitucionDeportiva instDepor = em.find(InstitucionDeportiva.class, inst);
		return instDepor;
	}
	
	public ArrayList<String> obtenerInstitucion(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("from institucion");
		
		List<InstitucionDeportiva> listInstitucion = query.getResultList();
		
		ArrayList<String> aRetornar = new ArrayList<>();
		for(InstitucionDeportiva i: listInstitucion) {
			aRetornar.add(i.getNombre());
		}
		return aRetornar;
		
	}
	
	
	//NO SE USA
	public boolean existeInstitucion(String nombre) {
		int i = 0;
		InstitucionDeportiva iD;
		boolean encontre=false;
		while ((i < instituciones.size()) && (!encontre)) {
			iD=instituciones.get(i);
		    if (iD.getNombre().equals(nombre)) {
		    	encontre=true;
		    }	
		    i++;
		}
		return encontre;			
	}

	public List<DtInstitucionD> obtenerDtInstitucion() {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("from institucion");
		
		List<InstitucionDeportiva> listInstitucion = query.getResultList();
		List<DtInstitucionD> aRetornar = new ArrayList<>();
		
		for(InstitucionDeportiva i: listInstitucion) {
			aRetornar.add(i.getDtInstitucion());
		}
		return aRetornar;

	}

}
