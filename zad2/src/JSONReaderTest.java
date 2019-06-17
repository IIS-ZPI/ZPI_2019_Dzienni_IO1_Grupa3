
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONReaderTest {
    @Test
    void parseJSON() {
        JSONReader jsonReader = new JSONReader();
        JSONObject jo = jsonReader.parseJSON("src\\JSONExample.json");
        assertEquals("John",jo.get("firstName"));
        assertEquals("Smith",jo.get("lastName"));
    }


}