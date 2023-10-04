import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class SerializeHumansJackson {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static String serialize(Person person) throws IOException {
        if (person == null) {
            throw new IllegalArgumentException("person is null");
        }
        StringWriter writer = new StringWriter();

        mapper.writeValue(writer, person);

        return writer.toString();
    }

    public static Person deserialize(String jsonString) throws IOException {
        try {
            return mapper.readValue(jsonString, Person.class);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
