package com.mapper;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Xml {

    public String toXml(Object src) throws Exception {

        Class<?> cls = src.getClass();
        StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

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

}
