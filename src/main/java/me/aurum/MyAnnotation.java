package me.aurum;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface MyAnnotation {
	public String name() default "aurum";
	int number() default 100;

	// value() 단독으로 사용시 이름 넣을 필요없음

}
