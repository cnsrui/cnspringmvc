package com.cnspringmvc.demo.controller;

import com.cnspringmvc.demo.domain.Product;
import com.cnspringmvc.demo.form.ProductForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    private static final Log logger = LogFactory.getLog(ProductController.class);

    @RequestMapping(value="/product_input")
    public String inputProduct() {
        logger.info("inputProduct called");
        return "ProductForm";
    }

    @RequestMapping(value="/product_save")
    public String saveProduct(ProductForm productForm, Model model) {
        logger.info("saveProduct called");
        // no need to create and instantiate a ProductForm
        // create Product
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        try {
            product.setPrice(Float.parseFloat(
                    productForm.getPrice()));
        } catch (NumberFormatException e) {
        }

        // 添加product实例，就像被添加到HttpServletRequest中访问一样。用于在视图中显示属性。
        model.addAttribute("product", product);
        return "ProductDetails";
    }
}
