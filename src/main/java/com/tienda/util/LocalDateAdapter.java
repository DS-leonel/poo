package com.tienda.util;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException; // Importante para manejar errores de parseo

public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {


    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;


    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {

        return new JsonPrimitive(date.format(FORMATTER));
    }


    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        try {
            return LocalDate.parse(json.getAsString(), FORMATTER);
        } catch (DateTimeParseException e) {

            throw new JsonParseException("Fecha con formato inválido, se esperaba '" +
                    FORMATTER.toString() + "', pero se recibió: \"" + json.getAsString() + "\"", e);
        }
    }
}