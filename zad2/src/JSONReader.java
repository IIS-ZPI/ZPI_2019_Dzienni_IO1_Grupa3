import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;


public class JSONReader  {


    public JSONReader() {
    }

    public JSONObject parseJSON(String json){
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader(json));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return (JSONObject) obj;
    }
}
