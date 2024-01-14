package paul.sp.labs.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import paul.sp.labs.models.BaseElement;

import java.io.IOException;

public class ElementDeserializer extends StdDeserializer<BaseElement> {
    public ElementDeserializer() {
        this(null);
    }

    public ElementDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public BaseElement deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonSerializer serializer = new JsonSerializer();
        JsonNode node = jp.getCodec().readTree(jp);
        String className = node.get("class").asText();

        switch (className) {
            case "Book":
                return serializer.DeserializeBookRecursive(node);
            default:
                return null;
        }
    }


}
