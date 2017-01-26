package lesson3;

public class ParallelStreams {
	
	public static void main(String[] args) {
		
		// Uma stream ou é parallel, ou é sequential. Nunca ambos
		
		// Podem haver, na pipeline de uma stream, várias conversões entre parallel
		// e sequential, mas sempre a última chamada é a que ganha.
		
		// Chamar concat() de uma sequential com uma parallel resulta em uma parallel
		
		// Parallel stream é implementada internamente utilizando o fork-join framework
		// Que é uma implementação para tirar vantagem dos múltiplos processadores de uma máquina
		
		// Pode ser que você não queira todos os processadores de uma máquina trabalnando em uma única task
		// É possível configurar isso (ForkJoinPool).
		
		// Remember: parallel streams always need more work to process (but they might finish it more quickly)
		// In the case of using the fork-join framework, all of that has overhead. 
		// You have to set up the framework, set up all the threads in the pool, you have to allocate jobs to the different queues, and so on.

		// Quanto maior o dataset, maior será a performance.
		
		/*
		 Parallel Streams source:
		 	ArrayList: GOOD (fácil de extrair dados de forma paralela)
		 	HashSet, TreeSet: OK
		 	LinkedList: BAD (has to follow all the links to find the value)
		 */
		
		/*
		 Operações:
		 	filter() e map() são excelentes. (Decompôem facilmente em paralelo)
		 	sorted() e distinct() não decompõem bem.
		 */
	}

}
