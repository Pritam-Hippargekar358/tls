package com.relationship.reflection;

import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.Base64;

public class ReflectionDemo {

    public static void main(String[] args) {
        BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(12);
        String tokenValue = Base64.getUrlEncoder().encodeToString(DEFAULT_TOKEN_GENERATOR.generateKey());
    }

    public void setField(Field field, Object target, Object value){
        try{
            field.set(target, value);
        }catch(Exception ex){
            handleReflectionException(ex);
            throw new IllegalStateException("Unexpected reflection exception -" + ex.getClass().getName()+":"+ex.getMessage());
        }
    }

    public Object invokeMethod(Method method, Object target, Object... args){
        try{
            return method.invoke(target, args);
        }catch(Exception ex){
            handleReflectionException(ex);
        }
        throw new IllegalStateException("Should never get here.");
    }

    public Method findMethod(Class<?> clazz, String name, Class<?>... paramTypes){
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method: methods){
            if(name.equals(method.getName()) && paramTypes.length == 0 || Arrays.equals(paramTypes, method.getParameterTypes())){
                return method;
            }
        }
        return null;
    }

    public void handleReflectionException(Exception ex){
        if(ex instanceof NoSuchMethodException){
            throw new IllegalStateException("Method not found:"+ex.getMessage());
        }
        if(ex instanceof IllegalStateException){
            throw new IllegalStateException("Could not access method:"+ex.getMessage());
        }
//        if(ex instanceof InvocationTargetException){}
        if(ex instanceof RuntimeException){
            throw  (RuntimeException) ex;
        }
        throw new UndeclaredThrowableException(ex);
    }

}
