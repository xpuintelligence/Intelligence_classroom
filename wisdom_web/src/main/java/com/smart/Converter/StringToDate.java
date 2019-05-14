package com.smart.Converter;

import com.smart.utils.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.SortedMap;


public class StringToDate implements Converter<String, DateTime> {

    @Override
    public DateTime convert(String source) {
        if (source.equals("")||source == null)
            return null;
        //获取第一个空格的位置
        return DateUtils.stringToDatetime(source);
    }


    @Test
    public void fun(){
        String source = "2019-10-10 00:00:00";
        DateTime dateTime = new DateTime(source);
        String s = dateTime.toString("yyyy-MM-dd HH:mm:ss");
        System.out.println(s);

    }


}
