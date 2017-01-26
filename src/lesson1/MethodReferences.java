package lesson1;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class MethodReferences {
	public static void main(String[] args) {
		
		Consumer<String> consuemr = System.out::println;
		Consumer<List<String>> listConsumer = ArrayList<String>::new;
		
		
		BiFunction<String, Integer, String> biFunction = (String s, Integer i) -> s.substring(i);
		BiFunction<String, Integer, String> biFunctionv2 = String::substring;
		
		List<String> list = new ArrayList<>();

		//UnaryOperator -> x = f(x)
		list.replaceAll(String::toUpperCase);
		
		list.sort((x,y) -> x.length() - y.length());
		
	}
	
	
	
	
	
}
