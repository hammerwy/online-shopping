package com.wy.shopping.common.service.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author wy
 * @date 2019/12/27
 * @description 布尔类型序列化为0，1
 */
public class OneZeroBooleanSerializer extends JsonSerializer<Boolean> {

    private final static String ONE_TRUE = "1";

    private final static String ZERO_FALSE = "0";

    @Override
    public void serialize(Boolean value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value) {
            gen.writeString(ONE_TRUE);
        } else {
            gen.writeString(ZERO_FALSE);
        }
    }
}
