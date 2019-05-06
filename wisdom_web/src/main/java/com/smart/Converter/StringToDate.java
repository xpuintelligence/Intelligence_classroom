package com.smart.Converter;

import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;


public class StringToDate implements Converter<String, DateTime> {

    @Override
    public DateTime convert(String source) {
        if (source.equals("")||source == null)
            return null;
        DateTime dateTime = new DateTime(source);
        return dateTime;
    }
}
