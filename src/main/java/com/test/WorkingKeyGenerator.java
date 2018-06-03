package com.test;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

/**
 * Considers the method in addition to the method arguments.
 *
 * <p>{@link org.springframework.cache.interceptor.SimpleKeyGenerator} only
 * considers the method arguments for caching. So if you have two different
 * methods that take the same {@link Integer} or {@link String} they will
 * interfere. In addition when you have two implementations of the same
 * interface the {@link Method} object that you will get in the
 * {@link #generate(Object, Method, Object...)} method will be the interface
 * method and therefore be the same for both implemetations.</p>
 */

@Component("workingKeyGenerator")
final class WorkingKeyGenerator implements KeyGenerator {

 // @Override
  public Object generate(Object target, Method method, Object... params) {
    return new WorkingKey(target.getClass(), method.getName(), params);
  }

  /**
   * Like {@link org.springframework.cache.interceptor.SimpleKey} but considers the method.
   */
  static final class WorkingKey {

    private final Class<?> clazz;
    private final String methodName;
    private final Object[] params;
    private final int hashCode;


    /**
     * Initialize a key.
     *
     * @param clazz the receiver class
     * @param methodName the method name
     * @param params the method parameters
     */
    WorkingKey(Class<?> clazz, String methodName, Object[] params) {
      this.clazz = clazz;
      this.methodName = methodName;
      this.params = params;
      int code = Arrays.deepHashCode(params);
      code = 31 * code + clazz.hashCode();
      code = 31 * code + methodName.hashCode();
      this.hashCode = code;
    }

    @Override
    public int hashCode() {
      return this.hashCode;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof WorkingKey)) {
        return false;
      }
      WorkingKey other = (WorkingKey) obj;
      if (this.hashCode != other.hashCode) {
        return false;
      }

      return this.clazz.equals(other.clazz)
          && this.methodName.equals(other.methodName)
          && Arrays.deepEquals(this.params, other.params);
    }

  }



}
