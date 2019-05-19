package com.cnspringmvc.demo.controller;

import com.cnspringmvc.demo.domain.Employee;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller

public class EmployeeController {
    
    private static final Log logger = LogFactory.getLog(ProductController.class);
    
    @RequestMapping(value="employee_input")
    public String inputEmployee(Model model) {
        model.addAttribute(new Employee());
        return "EmployeeForm";
    }

    /**
     * 执行此方法时，会自动执行 Converter 中的转换日期格式
     * @param employee
     * @param bindingResult 保存日期转换时的错误信息
     * @param model
     * @return
     */
    @RequestMapping(value="employee_save")
    public String saveEmployee(@ModelAttribute Employee employee, BindingResult bindingResult, Model model) {
        //如果日期转换出错，会显示错误信息，并跳转到EmployeeForm.jsp页面重新输入
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            logger.info("Code:" + fieldError.getCode() 
                    + ", field:" + fieldError.getField());
            return "EmployeeForm";
        }
        
        // save employee here
        
        model.addAttribute("employee", employee);
        return "EmployeeDetails";
    }
}
