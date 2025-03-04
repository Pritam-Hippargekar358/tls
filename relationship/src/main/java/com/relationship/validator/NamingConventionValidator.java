package com.relationship.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

@Component
public class NamingConventionValidator {
    private static final Logger logger = LoggerFactory.getLogger(NamingConventionValidator.class);

    private static final Pattern CLASS_NAME_PATTERN = Pattern.compile("([a-zA-Z_$][a-zA-Z\\d_$]*\\.)*[a-zA-Z_$][a-zA-Z\\d_$]*");
    private static final Pattern METHOD_NAME_PATTERN = Pattern.compile("[a-z][a-zA-Z0-9_]*");
    public void validateNamingConventions(String basePackage) {
        logger.info("Execute validateNamingConventions");
        String[] classNames = ClassScanner.getClassesInPackage(basePackage);

        for (String className: classNames) {
            if (!CLASS_NAME_PATTERN.matcher(className).matches()) {
                throw new RuntimeException("Class name violation: " + className);
            }

            Class<?> clazz;
            try {
                clazz = Class.forName(className);
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    System.out.print(method.getName());
                    if (!METHOD_NAME_PATTERN.matcher(method.getName()).matches()) {
                        throw new RuntimeException("Method name violation in class " + className + ": " + method.getName());
                    }
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}
