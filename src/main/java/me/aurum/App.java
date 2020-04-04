package me.aurum;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) throws ClassNotFoundException {
		// 인스턴스가 이미 있음
		Class<Book> bookClass = Book.class;
		Book book = new Book();
/*
		2
		Book book = new Book();
		Class<? extends Book> aClass = book.getClass();

		3
		Class<?> aClass1 = Class.forName("me.aurum.Book");*/

		//Field field = bookClass.getField();

		Arrays.stream(bookClass.getFields()).forEach(System.out::println); // getFields pubic field만 리턴 getField("이름")
		Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println); // 전체 field만 리턴

		// 필드접근
		Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
			try {
				f.setAccessible(true); // public이외에도 접근하도록 조작
				System.out.printf("%s %s\n", f, f.get(book));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		});

		// 메소드 접근
		Arrays.stream(bookClass.getMethods()).forEach(System.out::println);

		//constructor접근
		Arrays.stream(bookClass.getDeclaredConstructors()).forEach(System.out::println);

		//슈퍼클래스
		System.out.println(MyBook.class.getSuperclass());

		// 인터페치스
		Arrays.stream(bookClass.getInterfaces()).forEach(System.out::println);

		Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
			int modifiers = f.getModifiers();
			System.out.println(f); // 속성확인
			System.out.println(Modifier.isPrivate(modifiers)); // 프라이빗 속성확인
			System.out.println(Modifier.isStatic(modifiers)); // 스태틱

		});

		Arrays.stream(Book.class.getDeclaredMethods()).forEach(m -> {
			int modifiers = m.getModifiers();
			System.out.println(m); // 속성확인
			System.out.println(Modifier.isPrivate(modifiers)); // 프라이빗 속성확인
			System.out.println(Modifier.isStatic(modifiers)); // 스태틱
			// 리턴타입 /

		});

		// @Retention(RetentionPolicy.CLASS) 기분값 출력없음 -> 바이트코드까지만 들어가고, 메모리에는 반영되지 않음
		// @Retention(RetentionPolicy.RUNTIME) 수정시 출력됨
		// javap -c -v /Users/aurum/OneDrive/300.SRC/IDEA/refatoring-example/target/classes/me/aurum/Book.class
		Arrays.stream(bookClass.getDeclaredAnnotations()).forEach(System.out::println); // only 현재만 붙어있는것 @Inherited 제외

		Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
			Arrays.stream(f.getAnnotations()).forEach(a -> {
				if (a instanceof MyAnnotation) {
					MyAnnotation myAnnotation = (MyAnnotation) a;
					System.out.println(myAnnotation.name());
					System.out.println(myAnnotation.number());

				}
			});
		});


	}
}
