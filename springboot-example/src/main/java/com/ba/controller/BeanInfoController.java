package com.ba.controller;

import com.ba.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beans")
public class BeanInfoController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/all")
    public String[] getBeans() {
        String[] allBeanNames = context.getBeanDefinitionNames();

        for (String beanName : allBeanNames) {
            System.out.println(beanName);
        }

        return allBeanNames;
    }

    @GetMapping("/by-name")
    public String getBeanByName() {

        TodoService todoService = (TodoService) context.getBean("todoService");
        todoService.addNewTodo();

        return "bean found by name";
    }
}
