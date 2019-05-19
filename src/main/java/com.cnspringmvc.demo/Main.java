package com.cnspringmvc.demo;

import com.cnspringmvc.demo.bean.EmployeeOld;
import com.cnspringmvc.demo.bean.Products;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"spring-config.xml"});

        Products products1 = context.getBean("product", Products.class);
        products1.setName("Excellent snake oil");
        System.out.println("products1: " + products1.getName());
        Products products2 = context.getBean("product", Products.class);
        System.out.println("products2: " + products2.getName());
        System.out.println("------------");

        Products featuredProducts = context.getBean("featuredProduct", Products.class);
        System.out.println(featuredProducts.getName() + ", " + featuredProducts.getDescription()
                + ", " + featuredProducts.getPrice());

        Products featuredProducts2 = context.getBean("featuredProduct", Products.class);
        System.out.println(featuredProducts2.getName() + ", " + featuredProducts2.getDescription()
                + ", " + featuredProducts2.getPrice());
        System.out.println("------------");

        Calendar calendar = context.getBean("calendar", Calendar.class);
        System.out.println(calendar.getTime());
        System.out.println("------------");


        EmployeeOld employeeOld1 = context.getBean("employee1", EmployeeOld.class);
        System.out.println(employeeOld1.getFirstName() + " " + employeeOld1.getLastName());
        System.out.println(employeeOld1.getHomeAddress());
        System.out.println("------------");

        EmployeeOld employeeOld2 = context.getBean("employee2", EmployeeOld.class);
        System.out.println(employeeOld2.getFirstName() + " " + employeeOld2.getLastName());
        System.out.println(employeeOld2.getHomeAddress());

    }

}
