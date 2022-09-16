package interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.TableModel;

import datatypes.DtActDeportiva;
import datatypes.DtClase;
import datatypes.DtInstitucionD;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import excepciones.ActividadRepetidaExcepcion;
import excepciones.ClaseRepetidaExcepcion;
import excepciones.ExisteRegistroAClase;
import excepciones.InstitucionRepetidaExcepcion;
import excepciones.NoExisteClase;
import excepciones.NoExisteProfesor;
import excepciones.NoExisteUsuario;
import excepciones.NoesxisteSocio;
import excepciones.NoexsiteIntDep;
import excepciones.SinActDep;
import logica.ActividadDeportiva;
import logica.Clase;
import logica.InstitucionDeportiva;
import logica.Profesor;
import logica.Socio;
import logica.Usuario;
import excepciones.UsuarioRepetidoExcepcion;



public interface IControlador {

	void altaInstitucion(DtInstitucionD institucion) throws InstitucionRepetidaExcepcion;
	void altaActividad(DtActDeportiva actividad, String nombreIns) throws ActividadRepetidaExcepcion;
	void altaClase(DtClase clase, String nombreIns, String nomAct, String nombreP) throws ClaseRepetidaExcepcion;
	void agregarUsuario(DtUsuario user, String instituto) throws UsuarioRepetidoExcepcion;
	void registroDictadoClases(String nombreClase, String nombreAct, String nombreInstitucion, String socio) throws ExisteRegistroAClase;
	void modificarActDep(String intD, String actD, String descripcion, int duracion, float costo);
	void modificarUsuario(String nick, String nombre, String apellido, Date fechaN);
	void modificarInstitucion(String nombreInst, String descripcionInst, String urlInst);

	public DtActDeportiva elegirActividad(String nombreAct, String nombreInstitucion);
	public List<DtActDeportiva> obtenerActividadesDdeUnaInt(String nombreI)throws NoexsiteIntDep; // vendria a ser un listarActividadesDep
	public DtUsuario obtenerUsuario(String nombreU);
	public List<DtClase> listarDatoClasedeS(DtSocio socio)throws NoExisteClase;
	public List<DtClase> listarDatoClasedeP(DtProfesor profe) throws NoExisteClase;
	public DtClase elegirClase(String nombreClase, String nombreAct, String nombreInstitucion);
	public List<DtClase> listarDatoClase(String nombreAct, String nombreInstitucion);
	public List<DtActDeportiva> listarDatoActdepP(DtProfesor profe)throws SinActDep;
	public List<DtUsuario> ListarDtUsuario();
	public List<DtActDeportiva> rankingDeActividadesD();
	public List<DtClase> rankingDeClases();
	public List<DtInstitucionD> listadoDtIntiDtInstitucion()throws NoexsiteIntDep;
	
	
	
	public String[] listarInstituciones()throws NoexsiteIntDep;
	public String[] listarActividades(String institucion) throws SinActDep;
	public String[] listarProfesoresInt(String institucion) throws NoExisteProfesor;
	public String[] listarClase(String institucion, String actividadDep) throws NoExisteClase;
	public String[] listarSocios() throws NoesxisteSocio;
	public String[] ListarUsuario() throws NoExisteUsuario;	
	public String[] listarInstitucionesM()throws NoexsiteIntDep;


	
	
	

	
	
	
	

}