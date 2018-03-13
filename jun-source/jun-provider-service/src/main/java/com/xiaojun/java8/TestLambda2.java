package com.xiaojun.java8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;


public class TestLambda2 {

	
	
	public void consumertest(String str,Consumer<String> consumer){
		consumer.accept(str);
	}
	
	public String supplipertest(Supplier<String> supplier){
		return supplier.get();
	}
	
	public int functiontest(int a, Function<Integer, Integer> function){
		return function.apply(a);
	}
	
	public boolean predicatetest(Integer t,Predicate<Integer> predicate){
		return predicate.test(t);
	}
	
	@Test 
	public void test1(){
		consumertest("avadads", (x) -> System.out.println(x));
		
		String string = supplipertest(() -> "22");
		
		System.out.println(string);
		
		int a = functiontest(10, (x) -> x*x);
		
		System.out.println(a);
		
		boolean b = predicatetest(1, (x) -> x>2);
		
		System.out.println(b);
	}
}
