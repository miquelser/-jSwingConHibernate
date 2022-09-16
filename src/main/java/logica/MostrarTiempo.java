package logica;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MostrarTiempo {
	
	Calendar fecha = new GregorianCalendar();
	
	String anio = Integer.toString(fecha.get(Calendar.YEAR));
	String mes = Integer.toString(fecha.get(Calendar.MONTH));
	String dia = Integer.toString(fecha.get(Calendar.DATE));
	
	public String fechaComp = anio+"/"+mes+"/"+dia;
	
	String hora = Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));
	String min = Integer.toString(fecha.get(Calendar.MINUTE));
	
	public String horaComp = hora+":"+min;	
	
}
