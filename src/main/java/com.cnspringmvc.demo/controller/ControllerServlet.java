package com.cnspringmvc.demo.controller;

import com.cnspringmvc.demo.action.SaveProductAction;
import com.cnspringmvc.demo.form.ProductForm;
import com.cnspringmvc.demo.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ControllerServlet", urlPatterns = {"/input-product", "/save-product"})
public class ControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1579L;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {

        String uri = request.getRequestURI();
        /*
         * 解析url
         * 截取最后一个"/"后面的字符串，作为新的URL地址
         * 例如 /xxx1/xxx2/input-product 被解析成 /input-product
         */
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);

        // execute an action
        String dispatchUrl = null;
        if ("input-product".equals(action)) {
            // no action class, just forward
            dispatchUrl = "/WEB-INF/jsp/ProductForm.jsp";
        } else if ("save-product".equals(action)) {
            // create form 表单对象
            ProductForm productForm = new ProductForm();
            // populate action properties
            productForm.setName(request.getParameter("name"));
            productForm.setDescription(request.getParameter("description"));
            productForm.setPrice(request.getParameter("price"));

            // create model 领域对象
            Product product = new Product();
            product.setName(productForm.getName());
            product.setDescription(productForm.getDescription());
            try {
                product.setPrice(new BigDecimal(productForm.getPrice()));
            } catch (NumberFormatException e) {
            }
            // execute action method，执行对数据的处理，比如存储到数据库。暂时为空方法
            SaveProductAction saveProductAction =
                    new SaveProductAction();
            saveProductAction.save(product);

            // store model in a scope variable for the view，用于视图界面，可用EL表达式显示
            request.setAttribute("product", product);
            // 设置显示页面url
            dispatchUrl = "/WEB-INF/jsp/ProductDetails.jsp";
        }
            // 跳转页面操作
            // RequestDispatcher 的 forward 方法并不会停止执行剩余的方法。如果 forward 不是最后一行代码，则应显示的返回。
        if (dispatchUrl != null) {
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}
