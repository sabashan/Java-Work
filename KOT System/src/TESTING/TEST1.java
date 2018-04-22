package TESTING;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class TEST1 {
	
	public static void main(String[] args) {
		
		
		String time = new SimpleDateFormat("hh:mm a").format(Calendar.getInstance().getTime());
		
		System.out.println(time);
		
		
		
	}
	
	

}
