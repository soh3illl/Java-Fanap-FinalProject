package org.example;

import org.example.annotations.DeprecatedMethod;
import org.example.bank.BankAccount;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Set;

public class AnnotationResultViewer {
    public static void main(String[] args) {
        Class<BankAccount> bankAccountClass = BankAccount.class;
        Reflections reflections = new Reflections("org.example.bank");
        Set<Class<? extends BankAccount>> childClasses =
                reflections.getSubTypesOf(BankAccount.class);
        printDeprecatedMethodInfo(bankAccountClass);
        for (Class childClass:childClasses) {
            printDeprecatedMethodInfo(childClass);
        }
    }

    private static void printDeprecatedMethodInfo(Class bankAccountClass) {
        for (Method method : bankAccountClass.getMethods()) {
            if (method.isAnnotationPresent(DeprecatedMethod.class)){
                DeprecatedMethod annotation = method.getAnnotation(DeprecatedMethod.class);
                System.out.println(bankAccountClass.getName());
                System.out.println(method.getName());
                System.out.println(annotation.reason());
                System.out.println(annotation.replacement());
                System.out.println("-------------------------------------------------------");
            }
        }
    }
}