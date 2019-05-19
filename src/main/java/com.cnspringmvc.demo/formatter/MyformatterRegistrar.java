package com.cnspringmvc.demo.formatter;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

/**
 * @Description
 * @auther cnsrui
 * @create 2019-05-19 12:26
 */
public class MyformatterRegistrar implements FormatterRegistrar {
    private String dataPattern;

    public MyformatterRegistrar(String dataPattern) {
        this.dataPattern = dataPattern;
    }
    @Override
    public void registerFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addFormatter(new DateFormatter(dataPattern));
        // 下面可以注册更多的formatter
    }
}
