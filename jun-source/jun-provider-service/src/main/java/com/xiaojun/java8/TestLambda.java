package com.xiaojun.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Consumer;

import javax.sound.midi.Soundbank;

import org.junit.Test;

public class TestLambda {

	@Test
	public void test2() {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
		TreeSet<Integer> ts = new TreeSet<>(com);
	}

	List<Employee> employees = Arrays.asList(new Employee("张三", 18, 9999.99), new Employee("李四", 38, 5555.99),
			new Employee("王五", 50, 6666.99), new Employee("赵六", 16, 3333.33), new Employee("田七", 8, 7777.77));

	public List<Employee> filterEmployee(List<Employee> employees, MyPredicate<Employee> myPredicate) {

		List<Employee> emps = new ArrayList<>();

		for (Employee employee : employees) {
			if (myPredicate.test(employee)) {
				emps.add(employee);
			}
		}
		return emps;

	}

	@Test
	public void test3() {

		List<Employee> list = filterEmployee(employees, (e) -> e.getAge() > 38);
		list.forEach(System.out::println);
	}

	@Test
	public void test4(){
		employees.stream().filter((e) -> e.getSalary()>=5000).limit(2).forEach(System.out::println);
	
		employees.stream().map(Employee::getName).forEach(System.out::println);
	}
	
	@Test
	public void test5(){
		Runnable r = () -> System.out.println("hello lambda");
		r.run();
	}
	
	@Test
	public void test6(){
		Consumer<String> con = (x) -> System.out.println(x);
		con.accept("hello");
		
		Consumer<String> consumer = x -> System.out.println(x);
		con.accept("a");
  	}
	@Test
	public void test7(){
		Comparator<Integer> com = (x,y) -> {
			System.out.println("hello");
			return Integer.compare(x, y);
		};
		
		Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);
	}
	
	@Test
	public void test8(){
		Collections.sort(employees, (e1,e2) ->{
			if (e1.getAge() == e2.getAge()) {
				return e1.getName().compareTo(e2.getName());
			}else {
				return Integer.compare(e1.getAge(), e2.getAge());
			}
		});
		
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}
	
	public String strHandle(String str , MyFunction mf){
		return mf.getValue(str);
	}
	@Test
	public void test9(){
		String timStr = strHandle("\t\t\t 我是罗小俊", (str) -> str.trim());
		System.out.println(timStr);
		
		String upper = strHandle("adsg", (str) -> str.toUpperCase());
		System.out.println(upper);
		
		String newStr = strHandle("我是罗小俊", (str) -> str.substring(2,5));
		System.out.println(newStr);
		
		op(100L, 200L, (x,y) -> x+y);
		op(100L, 200L, (x,y) -> x*y);
	}
	
	public void op (Long l1, Long l2 , MyFunction2<Long, Long> mf){
		System.out.println(mf.getValue(l1, l2));
	}
}
