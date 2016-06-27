package gov.goias.sat2.conf.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ederbd on 24/06/16.
 */
public class DateAdapter extends XmlAdapter<String, Date> {

    //ISO 8601
    private String format = "yyyy-MM-dd'T'HH:mm:ss.sssZ";

    private DateFormat df = new SimpleDateFormat(format);

    public DateAdapter() {
        super();
    }

    public Date unmarshal(String s) throws Exception{

        try {

            return df.parse(s);

        } catch (ParseException e) {
            throw new Exception("Format: "+
                    format +
                    " Ex: 2000-01-01T01:01:01-0300");
        }
    }

    public String marshal(Date c) {
        return df.format(c);
    }

}