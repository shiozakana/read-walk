package com.rtmart.readwalk.core.util;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;

/**
 * JSON处理工具类
 */
public class JsonUtil {

    /**
     * 通用ObjectMapper
     */
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        // json->bean ,忽略bean中不存在的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // map->json ,忽略为null的key
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        // 解析器支持解析单引号
        mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        // 解析器支持解析特殊字符
        mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

    }

    /**
     * Bean->JSON bytes
     *
     * @param bean
     * @return JSON bytes
     */
    public static byte[] toJsonBytes(final Object bean) {

        try {
            return mapper.writeValueAsBytes(bean);
        } catch (final JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }

    }

    /**
     * Bean->JSON
     *
     * @param bean
     * @return JSON
     */
    public static String toJson(final Object bean) {

        if (bean == null) {
            return null;
        }

        try {
            return mapper.writeValueAsString(bean);
        } catch (final JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }

    }

    /**
     * JSON bytes->BEAN
     *
     * @param json
     *            bytes
     * @param clazz
     * @return bean
     */
    public static <T> T toBean(final byte[] json, final Class<T> clazz) {

        if (null == json) {
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (final Exception e) {
            throw new IllegalArgumentException(e);
        }

    }

    /**
     * JSON->BEAN
     *
     * @param json
     * @param clazz
     * @return bean
     */
    public static <T> T toBean(final String json, final Class<T> clazz) {

        if (null == json) {
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (final Exception e) {
            throw new IllegalArgumentException(e);
        }

    }

    /**
     * JSON->Bean
     *
     * @param json
     * @param clazz
     * @return bean
     */
    public static <T> T toBean(final String json, final Class<?> clazz, final Class<?>... parameterClasses) {

        if (null == json) {
            return null;
        }
        try {
            return mapper.readValue(json,
                    mapper.getTypeFactory().constructParametrizedType(clazz, clazz, parameterClasses));
        } catch (final Exception e) {
            throw new IllegalArgumentException(e);
        }

    }

    /**
     * JSON->List
     *
     * @param json
     * @param clazz
     * @return list
     */
    public static <T> List<T> toList(final String json, final Class<T> clazz) {

        if (null == json) {
            return null;
        }
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (final Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Bean->Bean
     *
     * @param source
     * @param target
     * @return target bean
     */
    public static <T> T convertBean(final Object source, final Class<T> target) {

        return toBean(toJson(source), target);
    }

    /**
     * JSON->JsonNode
     *
     * @param json
     * @return JsonNode
     */
    public static JsonNode toJsonNode(final String json) {

        if (null == json) {
            return null;
        }
        try {
            return mapper.readTree(json);
        } catch (final Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 格式化
     * @param jsonStr
     * @return
     */
    public static String formatJson(final String jsonStr) {

        if (null == jsonStr || "".equals(jsonStr)) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     * @param sb
     * @param indent
     */
    private static void addIndentBlank(final StringBuilder sb, final int indent) {

        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

    /**
     * Bean->JSON
     *
     * @param bean
     * @return JSON
     */
    public static String toFormatJson(final Object bean) {

        if (bean == null) {
            return null;
        }

        try {
            return formatJson(mapper.writeValueAsString(bean));
        } catch (final JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }

    }

}
