package util;

import java.io.Serializable;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Local implements Serializable {

	/* EVITA INCOMPATIBILIDADE */
	private static final long serialVersionUID = 1L;
	private static DateTime dateTime;
	private static DateTimeFormatter dateTimeFormatter;
	
	/* RETORNA A DATA ATUAL */
	public static String getDataAtual() {
		dateTime = new DateTime(DateTimeZone.forID("America/Sao_Paulo"));
	    dateTimeFormatter = DateTimeFormat.forPattern ("dd-MM-yyyy");
		return dateTimeFormatter.print(dateTime);
	}
	
	/* RETORNA A HORA ATUAL */
	public static String getHoraAtual() {
		dateTime = new DateTime(DateTimeZone.forID("America/Sao_Paulo"));
	    dateTimeFormatter = DateTimeFormat.forPattern("HH:mm:ss");
		return dateTimeFormatter.print(dateTime);
	}
	
}
