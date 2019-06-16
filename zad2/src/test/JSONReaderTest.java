import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JSONReaderTest {
    @Test
    void parseJSON() {
        try {
            JSONReader jsonReader = new JSONReader();
            JSONObject jo = jsonReader.readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/c/usd/?format=json");
            assertEquals("dolar amerykański", jo.get("currency"));
            assertEquals("USD", jo.get("code"));
        }catch(IOException ioe){
            fail("JSON nie pobrał/otworzył się poprawnie");
        }
    }

}