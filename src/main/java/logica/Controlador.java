package logica;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import interfaces.IControlador;
import persistencia.Conexion;
import datatypes.DtUsuario;
import datatypes.DtSocio;
import datatypes.DtActDeportiva;
import datatypes.DtClase;
import datatypes.DtInstitucionD;
import datatypes.DtProfesor;
import datatypes.DtRegistro;
import excepciones.ActividadRepetidaExcepcion;
import excepciones.ClaseRepetidaExcepcion;
import excepciones.ExisteRegistroAClase;
import excepciones.NoExisteProfesor;
import excepciones.NoExisteUsuario;
import excepciones.NoesxisteSocio;
import excepciones.InstitucionRepetidaExcepcion;
import excepciones.NoExisteClase;
import excepciones.UsuarioRepetidoExcepcion;
import excepciones.NoexsiteIntDep;
import excepciones.SinActDep;


public class Controlador implements IControlador{
	
	
	public Controlador() {

		super();
	}

	//CASO DE USO: ALTA INSTITUCION
	public void altaInstitucion(DtInstitucionD institucion) throws InstitucionRepetidaExcepcion {
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		if (mI.existeInstitucion(institucion.getNombre())){
			throw new InstitucionRepetidaExcepcion("La Institucion: "+ institucion.getNombre() + " ya existe en el Sistema");
		}else {	
			InstitucionDeportiva nuevaInstitucion= new InstitucionDeportiva(institucion.getNombre(),institucion.getDescripcion(),institucion.getUrl());
			mI.agregarInstitucion(nuevaInstitucion);	
		}		
	}
	
	
	//CASO DE USO: ALTA ACTIVIDAD
	public void altaActividad(DtActDeportiva actividad, String nombreIns) throws ActividadRepetidaExcepcion {
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva Institucion = mI.buscarInstitucion(nombreIns);			
		if(Institucion.buscarActividad(actividad.getNombre())!=null) {
			throw new ActividadRepetidaExcepcion("Actividad "+ actividad.getNombre()+ " ya existe, ingrese otro nombre");
		}else {
			Date fecha = new Date();
			ActividadDeportiva act = new ActividadDeportiva(actividad.getNombre(), actividad.getDescripcion(),actividad.getDuracion(),actividad.getCosto(),fecha);
			Institucion.agregarActDeportiva(act);
			//PERSISTENCIA
			Conexion conexion = Conexion.getInstancia();
			EntityManager em = conexion.getEntityManager();
			em.getTransaction().begin();
			em.persist(act);
			em.getTransaction().commit();
		}
	}	

	
	//CASO DE USO ALTA USUARIO
	@Override
	public void agregarUsuario(DtUsuario user, String instituto) throws UsuarioRepetidoExcepcion {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
	
		boolean exiteN=mU.existeNickname(user.getNickname());
		Boolean exiteE=mU.existeEmail(user.getEmail());
		
		if (exiteN || exiteE){
			if (exiteN && exiteE){
				throw new UsuarioRepetidoExcepcion("El usuario con el Nickname: "+ user.getNickname() + " y con el correo: "+ user.getEmail() + " ya existe en el Sistema");
			}
			if (exiteN) 
				throw new UsuarioRepetidoExcepcion("El usuario con el Nickname: "+ user.getNickname() + " ya existe en el Sistema");
			if (exiteE)
				throw new UsuarioRepetidoExcepcion("El correo: "+ user.getEmail() + " ya se a usado en el Sistema");
			
		}
		//Usuario nuevoUsuario = null;
		Socio socio = null;
		Profesor profe = null;
		
		if (user instanceof DtSocio) {
			socio = new Socio(user.getNickname(),user.getNombre(), user.getApellido(), user.getEmail(),user.getFechaNac()); 
			mU.altaUsuario(socio);
		} 
		if (user instanceof DtProfesor) {
			profe  = new Profesor(user.getNickname(),user.getNombre(), user.getApellido(), user.getEmail(), user.getFechaNac(),((DtProfesor) user).getDescripcion(), ((DtProfesor) user).getBiografia(), ((DtProfesor) user).getSitioWeb());
			ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
			InstitucionDeportiva inst = mI.buscarInstitucion(instituto);
			inst.agregarProfesor(profe);
			mU.altaUsuario(profe);
			//inst = ((DtProfesor) user).getInstitucion();
			
			
			
			//mU.addPofeAlInstituto(profe, inst);
			
	}
	}
	
	//CASO DE USO: ALTA CLASE
		public void altaClase(DtClase clase, String nombreIns, String nomAct,String nombreP) throws ClaseRepetidaExcepcion  {
			ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
			InstitucionDeportiva institucion=mI.buscarInstitucion(nombreIns);
			ActividadDeportiva actividad = institucion.buscarActividad(nomAct);
			ManejadorUsuario mU = ManejadorUsuario.getInstancia();
			
			
			if(actividad.existeClase(clase.getNombre())) {
				throw new ClaseRepetidaExcepcion("La clase"+ clase.getNombre()+"ya existe");				
			}else {

					Date fechaReg = new Date();
					//Profesor profe=institucion.getProfesordeIntD(nombreP);
					Profesor profe13=(Profesor) mU.buscarUsuario(nombreP);
					Clase c = new Clase( clase.getNombre(), clase.getFecha(), clase.getHoraInicio(),clase.getUrl(), fechaReg);
					c.setAct(actividad);
					c.setProfe(nombreP);
					actividad.setClase(c);	
					profe13.setClase(c);
					
					Conexion conexion = Conexion.getInstancia();
					EntityManager em = conexion.getEntityManager();

					em.getTransaction().begin();	
					em.persist(c);
					//em.persist(profe);
					em.getTransaction().commit();
						
			}
			
		}
	//CASO CONSULTA DE ALTA ACTIVIDAD DEPORTIVA
	
		public DtActDeportiva elegirActividad(String nombreAct, String nombreInstitucion) {
			ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
			InstitucionDeportiva institucionD = mI.buscarInstitucion(nombreInstitucion);
			ActividadDeportiva acD = institucionD.buscarActividad(nombreAct);
			DtActDeportiva dtActDep = acD.getDtActDeportiva();
			return dtActDep;
		}
		
		public DtClase elegirClase(String nombreClase, String nombreAct, String nombreInstitucion) {
			ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
			InstitucionDeportiva institucionD = mI.buscarInstitucion(nombreInstitucion);
			ActividadDeportiva acD = institucionD.buscarActividad(nombreAct);
			DtClase cL= acD.getDtclase(nombreClase);
			return cL;
		}
	// RANKING DE CLASES
		public Comparator<Clase> compararPorRegistros = new Comparator<Clase>() {
			@Override
			public int compare(Clase  c1, Clase  c2) {
				Integer tam1;
				Integer tam2;
				List<DtRegistro> r1 = c1.getDtregistros();
				List<DtRegistro> r2 = c2.getDtregistros();
				if((r1 == null) || (r2 ==null)) {
					if(r1 == null && r2 ==null){
						tam1 = 0;
						tam2 =0;
					}else if(r2 == null) {
						tam2 = 0;
						tam1 = Integer.valueOf(c1.getDtregistros().size());
					}else {
						 tam2 = Integer.valueOf(c2.getDtregistros().size());
						 tam1 = 0;
					}	
				}
				else {
					 tam1 = Integer.valueOf(c1.getDtregistros().size());
					 tam2 = Integer.valueOf(c2.getDtregistros().size());
				}
				return tam2.compareTo(tam1);
			}
			
		};
		public List<DtClase> rankingDeClases(){
			
			Conexion conexion = Conexion.getInstancia();
			EntityManager em = conexion.getEntityManager();
			
			Query query = em.createQuery("from Clase");
			
			List<Clase> rankingDeClases = query.getResultList();
			
			
			rankingDeClases.sort(compararPorRegistros);
			List<DtClase> informacionClases =new ArrayList<>();
			for(Clase c: rankingDeClases) {
				informacionClases.add(c.getDtClase());
			}
			
			return informacionClases;
			
		}
		
	//RANKIN DE ACTIVIDADES DEPORTIVAS
		public Comparator<ActividadDeportiva> compararPorRegistrosA = new Comparator<ActividadDeportiva>() {
			@Override
			public int compare(ActividadDeportiva  a1, ActividadDeportiva  a2) {
				Integer tam1;
				Integer tam2;
				List<DtClase> r1 = a1.getClases();
				List<DtClase> r2 = a2.getClases();
				if((r1 == null) || (r2 ==null)) {
					if(r1 == null && r2 ==null){
						tam1 = 0;
						tam2 =0;
					}else if(r2 == null) {
						tam2 = 0;
						tam1 = Integer.valueOf(a1.getClases().size());
					}else {
						 tam2 = Integer.valueOf(a2.getClases().size());
						 tam1 = 0;
					}	
				}
				else {
					 tam1 = Integer.valueOf(a1.getClases().size());
					 tam2 = Integer.valueOf(a2.getClases().size());
				}
				return tam2.compareTo(tam1);
			}

			
		};
		public List<DtActDeportiva> rankingDeActividadesD() {
			Conexion conexion = Conexion.getInstancia();
			EntityManager em = conexion.getEntityManager();
			
			Query query = em.createQuery("select a from ActividadDeportiva a");
			
			List<ActividadDeportiva> rankingDeAct = query.getResultList();
			
			
			rankingDeAct.sort(compararPorRegistrosA);
			List<DtActDeportiva> informacionActD =new ArrayList<>();
			for(ActividadDeportiva a: rankingDeAct) {
				informacionActD.add(a.getDtActDeportiva());
			}
			
			return informacionActD;
		}


	//CASO REGISTRO SOCIO A CLASE
		//----------------------------------------------------------------------------------------
		public void registroDictadoClases(String nombreClase, String nombreAct, String nombreInstitucion, String socio) throws ExisteRegistroAClase {
			ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
			InstitucionDeportiva institucionD = mI.buscarInstitucion(nombreInstitucion);
			ActividadDeportiva acD = institucionD.buscarActividad(nombreAct);
			Clase clase = acD.getclase(nombreClase);
			
			ManejadorUsuario mU = ManejadorUsuario.getInstancia();
			//buscar el usuario
			Socio resSocio = (Socio) mU.buscarUsuario(socio);
			
			Date fecha = new Date();
			
			if (resSocio.existeRegistro(clase.getNombre()))
				throw new ExisteRegistroAClase("El usuario ya está registrado a la clase");
				
			clase.setRegistro(fecha, resSocio);
			Conexion conexion = Conexion.getInstancia();
			EntityManager em = conexion.getEntityManager();
			em.getTransaction().begin();
			em.persist(clase);
			em.getTransaction().commit();					
	}
		
	//CASO CONSULTA CLASE
	
	public DtUsuario obtenerUsuario(String nombreU) {
		ManejadorUsuario mU= ManejadorUsuario.getInstancia();
		Usuario U = mU.buscarDtUsuario(nombreU);
		return U.getDtUsuario();
	}
	
	//Modificar Intitucion
	public void modificarInstitucion(String nombreInst, String descripcionInst, String urlInst) {
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva res = mI.buscarInstitucion(nombreInst);
		res.setDescripcion(descripcionInst);
		res.setUrl(urlInst);
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(res);
		em.getTransaction().commit();
	}
	
	
	
	
	//FUNCIONES AUXILIARES
		@Override
		public String[] listarActividades(String institucion) throws SinActDep{
			ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
			InstitucionDeportiva resInstitucion = mI.buscarInstitucion(institucion);
			if(resInstitucion.getActividadesD()!=null) {
				return resInstitucion.getActividadesD();
			}
			else {
				throw new SinActDep("No existen actividades creadas.");
			}
		

		
	}
	
	@Override
	public String[] listarInstituciones() throws NoexsiteIntDep {
		
		
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		ArrayList<String> institucion = mI.obtenerInstitucion();
		


		String[] institucion_ret = new String[institucion.size()+1];
		if(!institucion.isEmpty()) {
			institucion_ret[0]="Selecccionar";
	        int i=1;
	        for(String id:institucion) {
	        	institucion_ret[i]=id;
	        	i++;
	        }
	        return institucion_ret;
		}
		else {
			throw new NoexsiteIntDep("No existen Intitucion creadas.");
		}
		
	}
	
	@Override
	public String[] listarProfesoresInt(String institucion) throws NoExisteProfesor{
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva  intdeProf = mI.buscarInstitucion(institucion);
		if(intdeProf!=null) {
			return intdeProf.getProfesoresN();
		}
		else {
			throw new NoExisteProfesor("No existen Profesores.");
		}

	}
	@Override
	public String[] listarClase(String institucion, String actividadDep) throws NoExisteClase {
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva resInstitucion = mI.buscarInstitucion(institucion);
		ActividadDeportiva actD= resInstitucion.buscarActividad(actividadDep);
		if(actD.getNombresClases()!=null) {
			return actD.getNombresClases();
		}
		else {
			throw new NoExisteClase("No existen Clases.");
		}
	}
	
	public String[] listarSocios() throws NoesxisteSocio{
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		if (mU.listarSocios() != null) {
			String[] res = new String[mU.listarSocios().size()+1];
			res[0] = "Seleccionar";
			int i = 1;
			for(Usuario u : mU.listarSocios()) {
				res[i] = u.getNickname();
				i++;
			}
		return res;
		}else {
			throw new NoesxisteSocio("No existen socios registrados.");
		}
	}

	
	public List<DtClase> listarDatoClase(String nombreAct, String nombreInstitucion){
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva resInstitucion = mI.buscarInstitucion(nombreInstitucion);
		ActividadDeportiva actD= resInstitucion.buscarActividad(nombreAct);
		
		return actD.getClases();
	}
	
	public String[] ListarUsuario() throws NoExisteUsuario {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		if (mU.obtenerNickUsuarios() != null) {
			String[] res = new String[mU.obtenerNickUsuarios().size()+1];
			res[0] = "Seleccionar";
			int i = 1;
			for(String u : mU.obtenerNickUsuarios()) {
				res[i] = u;
				i++;
			}
		return res;
		}else {
			throw new NoExisteUsuario("No existen Usuarios registrados.");
		}
	}

	@Override
	public List<DtClase> listarDatoClasedeP(DtProfesor profe) throws NoExisteClase{	
		if(profe.getClases()== null)
			throw new NoExisteClase("El Profesor no tiene clases.");	
		ManejadorUsuario mI= ManejadorUsuario.getInstancia();
		Profesor p= (Profesor)mI.buscarUsuario(profe.getNickname());
		return p.getDtProfesores();
	}

	@Override
	public List<DtActDeportiva> listarDatoActdepP(DtProfesor profe) throws SinActDep {
		List<DtClase> Class= profe.getClases();
		ArrayList<DtActDeportiva> actdep= new ArrayList<>();

		for (DtClase c: Class) {
			actdep.add(c.getAct());
	
		}
		HashSet hs = new HashSet();
		hs.addAll(actdep);
		actdep.clear();
		actdep.addAll(hs);

		return actdep;
	}

	@Override
	public List<DtClase> listarDatoClasedeS(DtSocio socio) throws NoExisteClase {
		ManejadorUsuario mI= ManejadorUsuario.getInstancia();
		Socio s= (Socio)mI.buscarUsuario(socio.getNickname());
		List<Registro> reg = s.listarRegistros();
		if(reg==null)
			throw new NoExisteClase("El Socio no tiene clases.");
		ArrayList<DtClase> Class= new ArrayList<>();
		for(Registro r: reg) {
			Class.add(r.getDtClase());
		}
		return Class;
	}

	@Override
	public List<DtActDeportiva> obtenerActividadesDdeUnaInt(String nombreI) throws NoexsiteIntDep {
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva institucionD = mI.buscarInstitucion(nombreI);
		List<ActividadDeportiva> acD = institucionD.getActividadesDep();
		
		ArrayList<DtActDeportiva> dtActDep = new ArrayList<>(); 
		for (ActividadDeportiva a : acD) {
			dtActDep.add(a.getDtActDeportiva());
		}
		return dtActDep;

	}



	@Override
	public String[] listarInstitucionesM() throws NoexsiteIntDep {
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		ArrayList<String> institucion = mI.obtenerInstitucion();
		


		String[] institucion_ret = new String[institucion.size()];
		if(!institucion.isEmpty()) {
	        int i=0;
	        for(String id:institucion) {
	        	institucion_ret[i]=id;
	        	i++;
	        }
	        return institucion_ret;
		}
		else {
			throw new NoexsiteIntDep("No existen Intitucion creadas.");
		}
	}

	@Override
	public void modificarActDep(String intD, String actD, String descripcion, int duracion, float costo) {
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva intdep= mI.buscarInstitucion(intD);
		ActividadDeportiva AD=intdep.buscarActividad(actD);
		AD.setCosto(costo);
		AD.setDescripcion(descripcion);
		AD.setDuracion(duracion);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(AD);
		em.getTransaction().commit();
	}

	@Override
	public List<DtUsuario> ListarDtUsuario() {
		ManejadorUsuario mU= ManejadorUsuario.getInstancia();
		return mU.getDtUsuarios();
	}

	
	public void modificarUsuario(String nick, String nombre, String apellido, Date fechaN) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usuario = mU.buscarUsuario(nick);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setFechaNac(fechaN);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}

	@Override
	public List<DtInstitucionD> listadoDtIntiDtInstitucion() throws NoexsiteIntDep {
		ManejadorInstitucion mI= ManejadorInstitucion.getInstancia();
		if(mI.obtenerDtInstitucion()==null) {
			throw new NoexsiteIntDep("No existen Intituciones registrados."); 
		}	
		return mI.obtenerDtInstitucion();
	}



}
