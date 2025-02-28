package poof.models.Meta.Adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateSerializer implements JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(
        JsonElement element,
        Type type,
        JsonDeserializationContext ctx
    ) throws JsonParseException {
        return LocalDateTime.parse(element.getAsString());
    }
}
