package org.nta.lessons.lesson8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {

  CacheType cacheType() default CacheType.IN_MEMORY;

  String name() default "SaveData";

  long limit() default -1L;

  boolean zip() default false;
}
