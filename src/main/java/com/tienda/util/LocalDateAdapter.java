package com.tienda.util;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    // Formato estándar ISO para fechas (yyyy-MM-dd)
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        // Serializa LocalDate a una cadena en formato ISO
        if (date == null) {
            throw new JsonParseException("La fecha no puede ser nula durante la serialización.");
        }
        return new JsonPrimitive(date.format(FORMATTER));
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // Verifica si el elemento JSON es nulo o vacío
        if (json == null || json.getAsString().trim().isEmpty()) {
            throw new JsonParseException("El elemento JSON para la fecha no puede ser nulo o vacío.");
        }

        try {
            // Intenta parsear la fecha usando el formato ISO
            return LocalDate.parse(json.getAsString().trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            // Lanza una excepción detallada si el formato es inválido
            throw new JsonParseException("Formato de fecha inválido. Se esperaba '" +
                    FORMATTER.toString() + "', pero se recibió: \"" + json.getAsString() + "\"", e);
        }
    }
}