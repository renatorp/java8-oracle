package lesson2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

	public static void main(String[] args) {

		List<Credito> credList = new ArrayList<>();
		credList.add(new Credito(3));
		credList.add(new Credito(4));
		credList.add(new Credito(6));
		credList.add(new Credito(2));
		
		
		System.out.println(credList.stream().mapToInt(Credito::getScore).max().getAsInt());

		
		int[] array = new int[] {1,2,3,4,5,6,7,8,9};
		System.out.println(Arrays.stream(array).average().getAsDouble());
	
		// toArray()
		int[] array2 = Arrays.stream(array)
			.filter(i -> i > 5)
			.toArray();
		
		Pattern p = Pattern.compile(",");
		p.splitAsStream("1,2,3,4,5,6,7").forEach(System.out::print);
		System.out.println();
		
		System.out.println(Stream.of(1,2,3,4,5,6,7,8).anyMatch(Integer.valueOf(1)::equals));
		
		// Infinite stream
		// Stream.iterate(new Integer(1), i -> i+1 ).forEach(System.out::println);
		// Terminate infinite stream with Terminal Operations
		Stream.iterate(new Integer(0), i -> i+1 )
			.skip(10)
			.peek(System.out::println)
			.findFirst();
		// Usar findAny para parallelStreams
		
		
		List<String> strings = new ArrayList<>();
		strings.add("Dont deny yourself");
		strings.add("I'm the guy");
		strings.add("Honey, I'm home!!");

		strings.stream()
			.flatMap(l -> Stream.of(l.split(" ")))
			.collect(Collectors.toList());
		
		// Restricting: only one after the first
		strings.stream()
			.skip(1)
			.limit(1)
			.collect(Collectors.toList());
		
		// Sort
		strings.stream().sorted((x,y) -> x.length() - y.length());
		
		// pick for debugging / doing sth while the stream is being processed.
		strings.stream().peek(System.out::println).count();
		
		// Reduce operation
		String[] words = new String[] {"bla", "cheese", "mad", "cuddle"};
		Optional<String> value = Arrays.stream(words)
			.reduce((a, b) -> a + " >> " + b);
		
		// Optionals: stream with 1 or 0 values
		value.ifPresent(System.out::println);
		value.map(String::trim)
			.filter(x -> x.length() > 0)
			.ifPresent(System.out::println);
		
		// flatMap (optional inside optional)
		value.flatMap(Streams::tryFindSimilar)
		.ifPresent(System.out::println);
		
		String bla = "bla";
		String result = Optional.ofNullable(bla)
			.map(Streams::findSimilar)
			.orElse("None");
		System.out.println(result);
	}
	
	public static Optional<String> tryFindSimilar(String string) {
		return Optional.of(string);
	}
	
	public static String findSimilar(String string) {
		return null;
	}
}

class Credito{
	
	public Credito(int score) {
		super();
		this.score = score;
	}

	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}