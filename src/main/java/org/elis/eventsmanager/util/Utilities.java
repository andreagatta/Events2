package org.elis.eventsmanager.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilities {
    private static ObjectMapper getMapper(){
        ObjectMapper om=new ObjectMapper();
        JavaTimeModule module=new JavaTimeModule();
        module.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE));
        module.addDeserializer(LocalDate.class,new LocalDateDeserializer(DateTimeFormatter.ISO_DATE));
        module.addSerializer(LocalDate.class,new LocalDateSerializer(DateTimeFormatter.ISO_DATE));
        module.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE));
        om.registerModule(module);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,true);
        return om;
    }
    public static <R> R convertToObject(String json,Class<R> classeDellOggettoRisultante){
        try{
            return getMapper().readValue(json,classeDellOggettoRisultante);
        }catch (JsonProcessingException e){
            return null;
        }
    }

    public static <I> String convertToJson(I input){
        try {
            return getMapper().writeValueAsString(input);
        } catch (JsonProcessingException e) {
            return null;
        }
    }


}
