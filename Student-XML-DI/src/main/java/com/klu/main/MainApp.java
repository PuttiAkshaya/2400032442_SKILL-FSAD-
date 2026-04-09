package com.klu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.klu.model.Student;

public class MainApp {
    public static void main(String[] args) {

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Student st = (Student) ctx.getBean("student");
        st.display();
    }
}
