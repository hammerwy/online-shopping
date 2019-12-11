package com.wy.shopping.common.service.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyong
 * @description
 * @date 2019-04-23
 */
@Slf4j
public class JsonUtil {
    /**
     * mapper
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        // 统一日期格式yyyy-MM-dd HH:mm:ss
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        timeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //大小写不敏感设置
        MAPPER.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).registerModule(timeModule);
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    /**
     * 将对象转换成json字符串。
     */
    public static String toJson(Object data) {
        if (data == null) {
            return null;
        }
        if (data instanceof String) {
            return (String) data;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return MAPPER.writer(sdf).writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json结果集转化为对象
     */
    public static <T> T parse(String jsonData, Class<T> beanType) {
        try {
            if (StringUtils.isEmpty(jsonData)) {
                return null;
            }
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json数据转换成pojo对象list
     */
    public static <T> List<T> ofList(String jsonData, Class<T> type) {
        if (StringUtils.isEmpty(jsonData)) {
            return null;
        }
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, type);
            return MAPPER.readValue(jsonData, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把json数据转成map
     */
    public static Map<String, Object> ofMap(String jsonData) throws IOException {
        if (StringUtils.isEmpty(jsonData)) {
            return new HashMap<>(16);
        }

        TypeReference<Map<String, Object>> mapTypeReference = new TypeReference<Map<String, Object>>() {
        };
        return MAPPER.readValue(jsonData, mapTypeReference);
    }

    public static void main(String[] args) {

    }
}
