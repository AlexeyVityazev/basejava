package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume resume = new Resume("Hello");
        Class classResume = resume.getClass();
        Method method = classResume.getDeclaredMethod("toString", null);
        System.out.println(method.invoke(resume,null));
    }
}
