package acme.components;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilComponent {

	public static Date addFecha(final Date fechaBase, final int tipoFecha, final int cantidadSumar ) { //Permite sumarle dias, horas o cualquier unidad de tiempo a otra fecha y te devuelve un Date del resultado
		   final Calendar calendar = Calendar.getInstance();
		      calendar.setTime(fechaBase); 
		      calendar.add(tipoFecha, cantidadSumar);
		      return calendar.getTime();
	   }
	
	public static String currentDateToString() {
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		final Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
}
