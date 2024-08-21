import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class CustomPriceDeserializer extends JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String priceText = p.getText();
        return Double.valueOf(priceText.replace("$", ""));
    }
}
