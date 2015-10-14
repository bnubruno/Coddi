package coddi.com.br.App;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bruno on 27/06/2015.
 */
public class Macetes {

    public static String dateToString(Date data, String formato) {
        DateFormat df = new SimpleDateFormat(formato);

        if (data != null) {
            return df.format(data);
        }

        return "01/2012";
    }


}
