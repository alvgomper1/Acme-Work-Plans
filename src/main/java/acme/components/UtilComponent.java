package acme.components;

import java.util.Calendar;
import java.util.Date;

public class UtilComponent {

	

	public static Date addFecha(final Date fechaBase, final int tipoFecha, final int cantidadSumar ) { //Permite sumarle dias, horas o cualquier unidad de tiempo a otra fecha y te devuelve un Date del resultado
		   final Calendar calendar = Calendar.getInstance();
		      calendar.setTime(fechaBase); 
		      calendar.add(tipoFecha, cantidadSumar);
		      return calendar.getTime();
	   }
}
