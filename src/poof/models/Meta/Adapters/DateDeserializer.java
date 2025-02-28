package poof.models.Meta.Adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class DateDeserializer implements JsonSerializer<LocalDateTime> {
    @Override
    public JsonElement serialize(
        LocalDateTime date,
        Type type,
        JsonSerializationContext ctx
    ) {
        return new JsonPrimitive(date.toString());
    }
}
