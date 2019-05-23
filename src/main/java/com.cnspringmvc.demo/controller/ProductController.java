package com.cnspringmvc.demo.controller;

import com.cnspringmvc.demo.domain.Product;
import com.cnspringmvc.demo.service.UploadService;
import com.cnspringmvc.demo.service.UploadServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ProductController {

    private static final String UPLOAD_DIRECTORY = "images";

    private static final Log logger = LogFactory
            .getLog(ProductController.class);

    @RequestMapping(value = "/product_input")
    public String inputProduct(Model model) {
        model.addAttribute("product", new Product());
        return "ProductForm";
    }


    /**
     * 功能：
     * 添加 @Valid 注解，启用JSR308验证器
     * 或者使用 Validator 验证器
     * 保存错误信息，用于 jsp 页面显示
     * 添加 HttpServletRequest servletRequest 参数，用于上传文件模块，可以保存
     */
    @RequestMapping(value = "/product_save")
    //public String saveProduct( @ModelAttribute Product product, BindingResult bindingResult, Model model) {
    public String saveProduct(HttpServletRequest servletRequest, @Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        logger.info("product_save");

        // 使用验证器
        // 如果使用 JSR308，就不需要使用Validator验证器了
        //ProductValidator productValidator = new ProductValidator();
        //productValidator.validate(product, bindingResult);

        // 保存错误信息
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            logger.info("Code:" + fieldError.getCode() + ", field:"
                    + fieldError.getField());

            return "ProductForm";
        }

        // 创建保存上传文件的目录的路径来存储上传的文件，这个路径相对当前应用的目录，比如 myweb/
        String uploadPath = servletRequest.getServletContext().getRealPath("/") + UPLOAD_DIRECTORY;
        UploadService uploadService = new UploadServiceImpl();
        uploadService.uploadFiles(uploadPath,product);

        /*// 如果保存上传文件的目录不存在，就创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
            System.out.println("Upload Directory : "+ uploadDir + " has been created successfully.");
        }
        else{
            System.out.println("Upload Directory : "+ uploadDir + " had bean existed.");
        }

        List<MultipartFile> files = product.getImages();
        List<String> fileNames = new ArrayList<>();
        if (files != null && files.size() > 0) {
            for (MultipartFile multipartFile : files) {
                // 获取原始文件名称
                String fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
                // 根据保存上传文件的路径与原始文件名称，创建该文件
                String filePath = uploadPath  + File.separator+ fileName;
                File storeFile = new File(filePath);

                // 根据路径和原始文件名称，创建文件
                //File storeFile = new File(servletRequest.getServletContext().getRealPath("/images"), fileName);
                try {
                    // 保存已上传文件的核心方法，只需要 transferTo 即可
                    multipartFile.transferTo(storeFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("文件 " + fileName + " 上传成功。");
                System.out.println("保存路径为： " + storeFile);
            }
        }*/
        // save product here

        model.addAttribute("product", product);
        return "ProductDetails";
    }
}





/*

@Controller
public class ProductController {

    private static final Log logger = LogFactory.getLog(ProductController.class);

    // @Autowired注解会将 ProducService 接口的一个实例注入到这个类中
    @Autowired
    private ProductService productService;

    @RequestMapping(value="/product_input")
    public String inputProduct() {
        logger.info("inputProduct called");
        return "ProductForm";
    }

    */
/**
 * 重定向无法传值。由于需要经过客户端，Model中的所以值都会在重定向时丢失。
 * 可通过flash属性，使得重定向具有传值功能。
 * 使用flash属性传值的前提条件为
 * 1. springmvc-config配置文件中添加 <annotation-driven>
 * 2. 在方法的参数列表中添加 RedirectAttributes redirectAttributes
 * <p>
 * 使用可变路径
 * 1. 在 @RequestMapping 中添加变量，且放在{}中。
 * 2. 在方法签名中添加同名变量，且加上 @PahtVariable 注解。
 * 当方法被调用时，URL中的id值将被复制到路径变量id中，可被直接使用。
 * 路径变量的类型可以不为字符串。
 * <p>
 * 使用可变路径
 * 1. 在 @RequestMapping 中添加变量，且放在{}中。
 * 2. 在方法签名中添加同名变量，且加上 @PahtVariable 注解。
 * 当方法被调用时，URL中的id值将被复制到路径变量id中，可被直接使用。
 * 路径变量的类型可以不为字符串。
 * <p>
 * 使用可变路径
 * 1. 在 @RequestMapping 中添加变量，且放在{}中。
 * 2. 在方法签名中添加同名变量，且加上 @PahtVariable 注解。
 * 当方法被调用时，URL中的id值将被复制到路径变量id中，可被直接使用。
 * 路径变量的类型可以不为字符串。
 * <p>
 * 使用可变路径
 * 1. 在 @RequestMapping 中添加变量，且放在{}中。
 * 2. 在方法签名中添加同名变量，且加上 @PahtVariable 注解。
 * 当方法被调用时，URL中的id值将被复制到路径变量id中，可被直接使用。
 * 路径变量的类型可以不为字符串。
 *//*

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

    */
/**
 * 使用可变路径
 * 1. 在 @RequestMapping 中添加变量，且放在{}中。
 * 2. 在方法签名中添加同名变量，且加上 @PahtVariable 注解。
 * 当方法被调用时，URL中的id值将被复制到路径变量id中，可被直接使用。
 * 路径变量的类型可以不为字符串。
 *//*

    @RequestMapping(value = "/product_view/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        // 此处的 productService.get(id) 将返回在Map中最新添加的值
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "ProductView";
    }
}
*/
