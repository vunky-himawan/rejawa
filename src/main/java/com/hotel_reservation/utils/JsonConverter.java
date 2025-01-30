package com.hotel_reservation.utils;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class JsonConverter
        implements AttributeConverter<Map<String, Object>, String> {

    /**
     * ObjectMapper instance for JSON serialization and deserialization.
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Converts the given object to its JSON representation.
     *
     * @param attribute the object to be converted
     * @return the JSON representation of the object
     */
    @Override
    public String convertToDatabaseColumn(final Map<String, Object> attribute) {
        try {
            return OBJECT_MAPPER.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Error converting object to json: " + e);
        }
    }

    /**
     * Converts the given JSON representation to an object.
     *
     * @param dbData the JSON representation of the object
     * @return the object
     */
    @Override
    public Map<String, Object> convertToEntityAttribute(final String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(
                    dbData,
                    new TypeReference<Map<String, Object>>() {
                    });
        } catch (Exception e) {
            throw new RuntimeException("Error converting json to object: " + e);
        }
    }
}
