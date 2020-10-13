package com.everest.classload;

import com.everest.classload.classloader.MyClassLoader;
import com.everest.classload.pojo.Dog;

/**
 * @Date: 2020/9/13
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */

/**
 * 打破双亲委派机制，只需要继承ClassLoader，并重写loadClass()方法
 * 在逻辑中判断该类是否是需要我们自己加载的类，如果是则不向上委托，也即是不调用super.loadClass
 * 如果不是则向上委托加载，走双亲委派机制
 */
public class ClassLoadMainStart {
    private static volatile String path = "D:/Dev/MyRepo/java-study/out/production/classload";

    public static void main(String[] args)
        throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader(path);
        Class<?> aClass = myClassLoader.loadClass("com.everest.classload.pojo.Dog");
        Object obj = new Object();

        Object dog = aClass.newInstance();
        Dog dog1 = new Dog();

        System.out.println(dog.getClass() == dog1.getClass());

        System.out.println(myClassLoader.getParent());
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(aClass.getClassLoader());
    }
}
