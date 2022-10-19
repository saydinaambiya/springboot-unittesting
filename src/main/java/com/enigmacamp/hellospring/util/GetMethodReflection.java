package com.enigmacamp.hellospring.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetMethodReflection<T> {
    public static Method getMethod(Class clazz, String prop) {
        try {
            String classProd = prop.toLowerCase();
            return clazz.getMethod("get" + StringUtil.capitalize(classProd));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getMethodInvoke(T clazz, String prop) throws Exception {
        try {
            Method method = getMethod(clazz.getClass(), prop);
            return method.invoke(clazz);
        } catch (IllegalAccessException e) {
            throw new Exception("Can not access method");
        } catch (InvocationTargetException e) {
            throw new Exception("Wrong target");
        }
    }
}
