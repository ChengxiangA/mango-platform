package com.chengxiang.mango.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {

    /**
     * 调用指定方法名和参数的方法
     * @param object
     * @param methodName
     * @param args
     * @return
     */
    public static Object invoke(Object object,String methodName,Object... args) {
        Object result = null;
        Class<?> clazz = object.getClass();
        Method method = getMethod(clazz, methodName, args);
        if(method != null) {
            method.setAccessible(true);
            try {
                result = method.invoke(object,args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new NoSuchMethodException(clazz.getName() + "类中没有找到" + methodName + "方法");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取Method类
     * @param clazz
     * @param methodName
     * @param args
     * @return
     */
    public static Method getMethod(Class<?> clazz,String methodName,Object... args) {
        Method queryMethod = null;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            // 方法名一致，同时参数一致
            if(method.getName().equals(methodName) && args.length == method.getParameterCount()) {
                // 判断参数的类型是否一致
                boolean isSameMethod = true;
                Class<?>[] parameterTypes = method.getParameterTypes();
                for(int i = 0;i < method.getParameterCount();i ++) {
                    if(!parameterTypes[i].equals(args[i])) {
                        isSameMethod = false;
                        break;
                    }
                }
                if(isSameMethod) {
                    queryMethod = method;
                    break;
                }
            }
        }
        return queryMethod;
    }
}
