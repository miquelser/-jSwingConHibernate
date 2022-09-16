package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;

import datatypes.DtUsuario;
import persistencia.Conexion;
import datatypes.DtClase;
import datatypes.DtRegistro;
import datatypes.DtSocio;
@Entity
@DiscriminatorValue("S")
public class Socio extends Usuario{
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Registro> registros = new ArrayList<>();


	public Socio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Socio(String nickname, String nombre, String apellido, String email, Date fechaNac) {
		super(nickname, nombre, apellido, email, fechaNac);
		// TODO Auto-generated constructor stub
	}

	@Override
	public DtUsuario getDtUsuario() {
		ArrayList<DtRegistro> reguis = new ArrayList<DtRegistro>();
		
		
		if(this.registros.isEmpty()) {
			reguis=null;
		}
		else {
			List<Registro> reg=registros;
		
			for (Registro R : reg) {
				reguis.add(R.getDtReguistro());
			}
		}
		return new DtSocio(this.getNickname(), this.getNombre(), this.getApellido(), this.getEmail(), this.getFechaNac(),reguis);
	}

	public List<Registro> listarRegistros() {
		return registros;
	}
	
	public void setRegistro(Registro registro) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();	
		em.persist(registro);
		em.getTransaction().commit();

		this.registros.add(registro);
	}

	public boolean existeRegistro(String nombre) {
		boolean aRetornar = false;
		for(Registro r: registros) {
			if (r.getClase().getNombre().equals(nombre)) {
				aRetornar = true;
			}
		}
		return aRetornar;

	}

	/*public List<DtRegistro> getDtReguistros() {
		List<DtRegistro> Class= new ArrayList<>();
		
		for (Registro R : registros) {
			Class.add(R.getReguistro());
		}
		return Class;
	}*/

}
