package org.iesfm.company;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                CompanyConfiguration.class
        );

        context.getBean(Menu.class).run();
    }
}
