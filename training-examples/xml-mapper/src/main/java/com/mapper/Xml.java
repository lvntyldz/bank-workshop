package com.mapper;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Xml {
    private final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    public String toXml(Object src) throws Exception {

        Class<?> cls = src.getClass();
        StringBuilder builder = new StringBuilder(XML_HEADER);

        String className = cls.getSimpleName();
        builder.append("<" + className + ">");//root xml as className

        //handle each fields of class
        Arrays.stream(cls.getDeclaredFields()).forEach(field -> {

            //get class field name
            String fieldName = field.getName();

            try {

                Object value = getFieldValue(src, fieldName);

                builder.append("<" + fieldName + ">");//add fieldname as elemnt
                builder.append(value);//add field value
                builder.append("</" + fieldName + ">");//close element with fieldname

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        builder.append("</" + className + ">");

        return builder.toString();
    }

    private Object getFieldValue(Object src, String fieldName) throws Exception {
        Field field = src.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(src);
    }

    public <T> T fromXml(String data, Class<T> clazz) throws Exception {

        String singleData = data.replace(XML_HEADER, "");

        T t = clazz.getDeclaredConstructor().newInstance();

        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            String fieldName = field.getName();

            try {

                String xmlValue = StringUtils.substringBetween(singleData, "<" + fieldName + ">", "</" + fieldName + ">");

                Field f1 = clazz.getDeclaredField(fieldName);
                Class<?> type = f1.getType();

                f1.setAccessible(true);

                if (type.getName().equals("int")) {
                    f1.set(t, Integer.parseInt(xmlValue));
                } else {
                    f1.set(t, xmlValue);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        return t;
    }
}
