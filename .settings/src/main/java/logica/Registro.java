package logica;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import datatypes.DtClase;
import datatypes.DtRegistro;
import datatypes.DtSocio;

@Entity
@IdClass(RegistroID.class)
public class Registro {
	
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Id
	@ManyToOne
	private Socio socio;
	
	@Id
	@ManyToOne
	private Clase clase;
		
	public Registro() {
		super();
	}
	public Registro(Date fecha, Socio socio, Clase clase) {
		super();
		this.fecha = fecha;
		this.clase = clase;
		this.socio = socio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Clase getClase() {
		return clase;
	}
	public void setClase(Clase clase) {
		this.clase = clase;
	}
	
	public DtClase getDtClase() {
		Clase c=this.clase;
		if(c==null) {
			return null;
		}
		else {
			return c.getDtClase();
		}
	}
	
	
	public DtRegistro getDtReguistro() {
		return new DtRegistro(this.fecha);
	}


}
