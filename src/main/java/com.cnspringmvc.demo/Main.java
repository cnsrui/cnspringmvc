package com.cnspringmvc.demo;

import com.cnspringmvc.demo.bean.Employee;
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


        Employee employee1 = context.getBean("employee1", Employee.class);
        System.out.println(employee1.getFirstName() + " " + employee1.getLastName());
        System.out.println(employee1.getHomeAddress());
        System.out.println("------------");

        Employee employee2 = context.getBean("employee2", Employee.class);
        System.out.println(employee2.getFirstName() + " " + employee2.getLastName());
        System.out.println(employee2.getHomeAddress());

    }

}
