package com.cnspringmvc.demo.controller;

import com.cnspringmvc.demo.domain.Product;
import com.cnspringmvc.demo.form.ProductForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SaveProductController implements Controller {

    private static final Log logger = LogFactory
            .getLog(SaveProductController.class);

    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        logger.info("SaveProductController called");
        ProductForm productForm = new ProductForm();
        // populate action properties
        productForm.setName(request.getParameter("name"));
        productForm.setDescription(request.getParameter("description"));
        productForm.setPrice(request.getParameter("price"));

        // create model
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        try {
            product.setPrice(Float.parseFloat(productForm.getPrice()));
        } catch (NumberFormatException e) {
        }

        // insert code to save Product
        // 视图与模型一个返回
        //return new ModelAndView("/WEB-INF/jsp/ProductDetails.jsp", "product", product);
        return new ModelAndView("ProductDetails", "product", product);
    }

}
