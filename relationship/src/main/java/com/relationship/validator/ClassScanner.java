package com.relationship.validator;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class ClassScanner {
    public static String[] getClassesInPackage(String basePackage) {
        List<String> classNames = new ArrayList<>();
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(Object.class));
        Set<BeanDefinition> components = scanner.findCandidateComponents(basePackage);
        for (BeanDefinition bd : components) {
            classNames.add(bd.getBeanClassName());
        }

        return classNames.toArray(new String[0]);
    }
}
