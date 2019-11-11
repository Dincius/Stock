package stock;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {

	 private String dateStr;
	 
	    public void validDate(String dateFormat) {
	        this.dateStr = dateFormat;
	    }
	
	    public boolean isDateValid(String dateStr) {
	     
	    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
 
        format.setLenient(false);

        try {
            format.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
	}
}
