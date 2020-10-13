package com.everest.classload.classloader;

import java.io.File;
import java.nio.file.Files;

/**
 * @Date: 2020/9/13
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
public class MyClassLoader extends ClassLoader {

  private String rootPath;

  public MyClassLoader(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
    int i = name.lastIndexOf('.');
    if (i != -1) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null) {
        sm.checkPackageAccess(name.substring(0, i));
      }
    }

    // The class of the given name is not found in the parent
    // class loader as well as its local URLClassPath.
    // Check if this class has already been defined dynamically;
    // if so, return the loaded class; otherwise, skip the parent
    // delegation and findClass.
    Class<?> c = findLoadedClass(name);
    if (c != null) {
      if (resolve) {
        resolveClass(c);
      }
      return c;
    }
    if (name.startsWith("com.everest")) {
      return findClass(name);
    } else {
      return super.loadClass(name, resolve);
    }

  }

  @Override
  protected Class<?> findClass(final String name) throws ClassNotFoundException {
    String path = rootPath.concat("/").concat(name.replace('.', '/')).concat(".class");
    try {
      byte[] readAllBytes = Files.readAllBytes(new File(path).toPath());
      return defineClass(name, readAllBytes, 0, readAllBytes.length);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
