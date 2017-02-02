package com.javarush.test.level32.lesson08.home01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by sas on 13.11.16.
 */
public class CustomInvocationHandler implements InvocationHandler {
    private SomeInterfaceWithMethods someInterfaceWithMethods;

    public CustomInvocationHandler(SomeInterfaceWithMethods someInterfaceWithMethods) {
        this.someInterfaceWithMethods = someInterfaceWithMethods;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println(method.getName() + " in");
        method.invoke(someInterfaceWithMethods, objects);
        System.out.println(method.getName() + " out");
        return null;
    }
}
