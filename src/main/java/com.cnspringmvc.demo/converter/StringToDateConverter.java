package com.cnspringmvc.demo.converter;
// Spring 的 Converter 接口，用于将一种对象类型转换成另一种对象类型，需实现其 convert 方法。
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 需要在spring-config文件中配置
 */
public class StringToDateConverter implements Converter<String, Date> {

    private String datePattern;

    public StringToDateConverter(String datePattern) {
        this.datePattern = datePattern;
    }

    /**
     * 该方法是在何时执行的呢？
     * 是在Controller中路径映射的方法中，默认识别日期字段并执行吗？
     */
    @Override
    public Date convert(String s) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
            dateFormat.setLenient(false);
            //parse()是SimpleDateFormat的日期格式化方法
            return dateFormat.parse(s);
        } catch (ParseException e) {
            // the error message will be displayed when using <form:errors>
            throw new IllegalArgumentException(
                    "invalid date format. Please use this pattern\""
                            + datePattern + "\"");
        }
    }
}
