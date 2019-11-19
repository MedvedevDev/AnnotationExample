package com.anno;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyRepeatAnnos.class)
@interface What{
    String desc() default "Defails";
}
@Retention(RetentionPolicy.RUNTIME)
@interface MyNotation{
    String str();
    int val();
    int hal();
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyRepeatAnnos{
    What[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@interface MySingle{
    int value();
}

public class Main {
    @MySingle(123)
    @What()
    @What(desc = "new desc")
    @MyNotation(str = "Example", val = 1, hal = 2)
    public static void MyMath(boolean d, int n){
        Main ob =  new Main();

        try {
            Class<?> c = ob.getClass();
            Annotation annos[] = c.getAnnotations();

            Method m = c.getMethod("MyMath", boolean.class, int.class);
            MyNotation anno = m.getAnnotation(MyNotation.class);
            What w = m.getAnnotation(What.class);
//            System.out.println(w.desc());
            System.out.println(anno.str()+ " " + anno.val() + " " + anno.hal());
            System.out.println("---------------------");
            System.out.println(m.isAnnotationPresent(What.class));
            System.out.println(m.isAnnotationPresent(MyNotation.class));
            annos = m.getAnnotations();
            for (Annotation a:annos){
                System.out.println(a);
            }

            System.out.println("---------------------");
            Annotation[] annos1 = m.getAnnotationsByType(What.class);
            for (Annotation a:annos1){
                System.out.println(a);
            }

        } catch (NoSuchMethodException e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        MyMath(true, 1);
    }
}
