package datatypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logica.Registro;

public class DtSocio extends DtUsuario {
	
	private List<DtRegistro> registros = new ArrayList<>();
	
	
	
	public DtSocio(String nickname, String nombre, String apellido, String email, Date fechaNac,
			List<DtRegistro> registros) {
		super(nickname, nombre, apellido, email, fechaNac);
		this.registros = registros;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public List<DtRegistro> getRegistros() {
		return registros;
	}

}
