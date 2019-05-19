package com.cnspringmvc.demo.validator;

import com.cnspringmvc.demo.domain.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

public class ProductValidator implements Validator {

    // 验证器是否能处理指定的Class，这里是 Product 类。如果能，返回true
    @Override
    public boolean supports(Class<?> klass) {
        return Product.class.isAssignableFrom(klass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        // 使用验证器工具类创建错误信息
        ValidationUtils.rejectIfEmpty(errors, "name", "product.name.required");
        ValidationUtils.rejectIfEmpty(errors, "price", "price.required");
        ValidationUtils.rejectIfEmpty(errors, "productionDate", "productiondate.required");
        Float price = product.getPrice();
        // 自定义的验证器错误信息，效果同使用验证器工具类一致
        if (price != null && price < 0) {
            errors.rejectValue("price", "price.negative");
        }
        Date productionDate = product.getProductionDate();
        if (productionDate != null) {
            // 与今天的日期比较
            if (productionDate.after(new Date())) {
                errors.rejectValue("productionDate", "productiondate.invalid");
            }
        }
    }

    //validate()返回一个包含错误信息的字符串列表。如果返回一个空的列表，则表示输入合法。
   /* public List<String> validate(ProductForm productForm) {
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
    }*/

}
