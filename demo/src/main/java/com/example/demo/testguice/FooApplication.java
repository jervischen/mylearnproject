package com.example.demo.testguice;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2018-02-08 17:29.
 *
 * @author chenxiao
 */
public class FooApplication {
    private static Logger logger = LoggerFactory.getLogger(FooApplication.class);
    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new ModuleA());
        A instance = injector.getInstance(A.class);
        instance.printA();
    }

}
