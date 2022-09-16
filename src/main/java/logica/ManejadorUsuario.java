package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import persistencia.Conexion;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;
	private List<Usuario> usuarios = new ArrayList<>();
	
	private ManejadorUsuario(){}
	
	public static ManejadorUsuario getInstancia() {
		if (instancia == null)
			instancia = new ManejadorUsuario();
		return instancia;
	}
	//AGREGA EL USUARIO A LA TABLA.
	public void altaUsuario(Usuario user) {
		usuarios.add(user);
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		em.getTransaction().begin();	
		em.persist(user);
		em.getTransaction().commit();
	}
	
	//RETORNA UN USUARIO CON EL NICKNAME RECIBIDO
	public Usuario buscarDtUsuario(String nickname) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Usuario user = em.find(Usuario.class, nickname);
		return user;
	}
	
	public Usuario buscarUsuario(String nickname) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Usuario user = em.find(Usuario.class, nickname);
		return user;
	}
	
	//OBTENER LISTA DE USUARIOS POR NICKNAME
	public ArrayList<String> obtenerNickUsuarios(){
			Conexion conexion = Conexion.getInstancia();
			EntityManager em = conexion.getEntityManager();
			
			Query query = em.createQuery("select u from usuario u");
			List<Usuario> listUsuarios = (List<Usuario>) query.getResultList();
			
			ArrayList<String> aRetornar = new ArrayList<>();
			for(Usuario i: listUsuarios) {
				aRetornar.add(i.getNickname());
			}
			return aRetornar;
	}

	//Los existe se pueden controlar con la persistencia? al no dejar sobreescribir quizas podemos poner una excepcion
	
	public boolean existeNickname(String nickname) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Query query = em.createQuery("select u from usuario u");
        @SuppressWarnings("unchecked")
		List<Usuario> retorno = query.getResultList();
        boolean aretornar=false;;
        for (Usuario usuario : retorno) {
			if(usuario.getNickname().equals(nickname)) {
				return true;
			}
		}
        
        return aretornar;
	}
	
	public boolean existeEmail(String email) {
		Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Query query = em.createQuery("select u from usuario u");
        @SuppressWarnings("unchecked")
		List<Usuario> retorno = query.getResultList();
        boolean aretornar=false;;
        for (Usuario usuario : retorno) {
			if(usuario.getEmail().equals(email)) {
				return true;
			}
		}
        
        return aretornar;
	}

	public boolean esunProfesor(String profes) {
		boolean aret=false;
		Usuario usuario = this.buscarUsuario(profes);
		if(usuario!=null && usuario instanceof Profesor) {
			aret=true;
		}
		return aret;
	}
	
	public ArrayList<Usuario> listarSocios(){
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Query query = em.createQuery("select u from usuario u");

        @SuppressWarnings("unchecked")
		List<Usuario> retorno = query.getResultList();
        ArrayList<Usuario> res = new ArrayList<>();
        for(Usuario u : retorno) {
        	if (u instanceof Socio) {
        		res.add(u);
        	}
        }

		return res;
	}

	public List<DtUsuario> getDtUsuarios() {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("select u from usuario u");
		
		List<Usuario> Users = query.getResultList();
		List<DtUsuario> aretUsers= new ArrayList<>();
		
		for (Usuario U : Users) {
			aretUsers.add(U.getDtUsuario());
		}
		return aretUsers;
	}
	


	
	
} 
