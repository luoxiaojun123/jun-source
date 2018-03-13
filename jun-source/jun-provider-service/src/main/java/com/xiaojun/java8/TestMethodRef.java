package com.xiaojun.java8;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.Test;

public class TestMethodRef {

	@Test
	public void test1(){
		Consumer<String> consumer = System.out::println;
		consumer.accept("asda");
		
		Employee employee = new Employee();
		Supplier<String> supplier = employee::getName;
		String string = supplier.get();
		System.out.println(string);
		
	}
}
