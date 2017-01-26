package lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdvancedStreams {
	
	public static void main(String[] args) {
		
		String[] strings = new String[] {"A", "Teste", "Minas Gerais", "Java 8" };
		
		// Obter a maior string
		String string = Arrays.stream(strings)
			.reduce((partialResult, next) -> {
				if (partialResult.length() > next.length()) {
					return partialResult;
				}
				return next;
			}).get();
		System.out.println("Maior string: " + string);
		
		// Versão mais simples
		string = Arrays.stream(strings)
				.max(Comparator.comparingInt(String::length))
				.get();
		System.out.println("Maior string: " + string);
		
		
		//Infinite Streams ( terminar stream com findFirst, findAny ou forEach (consome sem terminar)
		
		// Não pode haver mudança de estado em Streams.
		
		//Collectors
		List<Aluno> alunos = new ArrayList<>();
		alunos.add(new Aluno("5341234", 25, "male"));
		alunos.add(new Aluno("345284", 5, "female"));
		alunos.add(new Aluno("345292", 2, "male"));
		alunos.add(new Aluno("098345", 2, "female"));
		alunos.add(new Aluno("8943759", 44, "female"));
		
		Map<String, Integer> mapScoreByAluno = alunos.stream()
			.collect(Collectors.toMap(s -> s.getId(), s-> s.getScore()));
		System.out.println(mapScoreByAluno.get("098345"));
		
		// Usando merge function
		Map<Integer, String> mapIdAlunosByScore = alunos.stream()
			.collect(Collectors.toMap(s -> s.getScore(), s -> s.getId(), (x, y) -> x + ", " + y));
		System.out.println(mapIdAlunosByScore.get(2));
		
		// Map<Integer, List<Aluno> !!!!
		Map<Integer, List<Aluno>> mapAlunosByScore = alunos.stream()
			.collect(Collectors.groupingBy(Aluno::getScore));
		System.out.println(mapAlunosByScore.get(2).get(0).getId() + ", " + mapAlunosByScore.get(2).get(1).getId());
		
		// Count
		Map<Integer, Long> mapNumAlunosByScore = alunos.stream()
			.collect(Collectors.groupingBy(Aluno::getScore, Collectors.counting()));
		System.out.println(mapNumAlunosByScore.get(2));
		
		// Joining values
		String idsAlunos = alunos.stream()
			.map(Aluno::getId)
			.collect(Collectors.joining(", "));
		System.out.println(idsAlunos);
		
		// PartitioningBy
		Map<Boolean, List<Aluno>> alunosByScoreGT20 = alunos.stream()
			.collect(Collectors.partitioningBy(s -> s.getScore() > 20));
		System.out.println(alunosByScoreGT20.get(false).size());
		
		// Mapping
		Map<String, List<Integer>> scoreListByGender = alunos.stream()
			.collect(Collectors.groupingBy(Aluno::getGender, Collectors.mapping(Aluno::getScore, Collectors.toList())));
		System.out.println(scoreListByGender.get("female"));
		
		// MaxBy
		Map<String, Optional<Aluno>> alunoMaxScoreByGender = alunos.stream()
			.collect(Collectors.groupingBy(Aluno::getGender, Collectors.maxBy(Comparator.comparingInt(Aluno::getScore))));
		System.out.println(alunoMaxScoreByGender.get("female").get().getId());
		
		Map<String, Optional<Integer>> maxScoreByGender = alunos.stream()
				.collect(Collectors.groupingBy(Aluno::getGender, Collectors.mapping(Aluno::getScore, Collectors.maxBy(Comparator.comparingInt(Integer::intValue)))));
		System.out.println(maxScoreByGender.get("female").get());
	}

}

class Aluno {
	private String id;
	private Integer score;
	private String gender;
	
	public Aluno(String id, Integer score, String gender) {
		super();
		this.id = id;
		this.score = score;
		this.gender = gender;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
