package com.cnspringmvc.demo.controller;

import com.cnspringmvc.demo.domain.Product;
import com.cnspringmvc.demo.form.ProductForm;
import com.cnspringmvc.demo.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private static final Log logger = LogFactory.getLog(ProductController.class);

    // @Autowired注解会将 ProducService接口的一个实例注入到这个类中
    @Autowired
    private ProductService productService;

    @RequestMapping(value="/product_input")
    public String inputProduct() {
        logger.info("inputProduct called");
        return "ProductForm";
    }

    /**
     * 重定向无法传值。由于需要经过客户端，Model中的所以值都会在重定向时丢失。
     * 可通过flash属性，使得重定向具有传值功能。
     * 使用flash属性传值的前提条件为
     * 1. springmvc-config配置文件中添加 <annotation-driven>
     * 2. 在方法的参数列表中添加 RedirectAttributes redirectAttributes
     */
    @RequestMapping(value="/product_save", method = RequestMethod.POST)
    public String saveProduct(ProductForm productForm, RedirectAttributes redirectAttributes) {
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

        // model.addAttribute()添加product实例，就像被添加到HttpServletRequest中访问一样。用于在视图中显示属性。
        //model.addAttribute("product", product);

        // ProductService接口可以提供数据的存储方法，比如数据库。
        Product savedProduct = productService.add(product);

        // 重定向传值
        redirectAttributes.addFlashAttribute("message", "The product was successfully added.");
        // 此处的 savedProduct.getId() 将返回在Map中最新添加值的id
        return "redirect:/product_view/" + savedProduct.getId();

    }

    /**
     * 使用可变路径
     * 1. 在 @RequestMapping 中添加变量，且放在{}中。
     * 2. 在方法签名中添加同名变量，且加上 @PahtVariable 注解。
     * 当方法被调用时，URL中的id值将被复制到路径变量中，可被直接使用。
     * 路径变量的类型可以不为字符串。
     */
    @RequestMapping(value = "/product_view/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        // 此处的 productService.get(id) 将返回在Map中最新添加的值
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "ProductView";
    }
}
