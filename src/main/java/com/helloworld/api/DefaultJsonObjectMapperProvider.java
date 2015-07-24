package com.helloworld.api;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.inject.Singleton;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Provider
@Singleton
public class DefaultJsonObjectMapperProvider implements ContextResolver<ObjectMapper>, com.google.inject.Provider<ObjectMapper> {

    //http://nexus-mopunch.rhcloud.com/content/repositories/thirdparty/com/gaebler/rest/gaebler-rest/0.0.1-GA/
    private static ObjectMapper objectMapper;

    private static synchronized void initInstance() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();

            @SuppressWarnings("unused")
            SerializationConfig serializationConfig = objectMapper.getSerializationConfig();
            @SuppressWarnings("unused")
            DeserializationConfig deserializationConfig = objectMapper.getDeserializationConfig();

            //date ISO format "2013-01-01T10:58:00.000+0000"
            //objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            objectMapper.setDateFormat(dateFormat);


            objectMapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        }
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return get();
    }

    @Override
    @Singleton
    public ObjectMapper get() {
        if (objectMapper == null) {
            initInstance();
        }
        return objectMapper;
    }

}

