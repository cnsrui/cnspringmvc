package com.cnspringmvc.demo.validator;

import com.cnspringmvc.demo.form.ProductForm;

import java.util.ArrayList;
import java.util.List;


/**
 * validate()返回一个包含错误信息的字符串列表。
 * 如果返回一个空的列表，则表示输入合法。
 */
public class ProductValidator {

    public List<String> validate(ProductForm productForm) {
        List<String> errors = new ArrayList<String>();
        String name = productForm.getName();
        // trim()返回此字符串移除了前导和尾部空白的副本；如果没有前导和尾部空白，则返回此字符串。此方法可用于截去字符串开头和末尾的空白。
        if (name == null || name.trim().isEmpty()) {
            errors.add("Product must have a name");
        }
        String price = productForm.getPrice();
        if (price == null || price.trim().isEmpty()) {
            errors.add("Product must have a price");
        } else {
            try {
                Float.parseFloat(price);
            } catch (NumberFormatException e) {
                errors.add("Invalid price value");
            }
        }
        return errors;
    }
}
