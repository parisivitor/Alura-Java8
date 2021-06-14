package br.com.alura.teste;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Curso {
	private String nome;
	private int alunos;

	public Curso(String nome, int alunos) {
		this.nome = nome;
		this.alunos = alunos;
	}

	public String getNome() {
		return nome;
	}

	public int getAlunos() {
		return alunos;
	}
}

public class TesteStream {

	public static void main(String[] args) {
		List<Curso> cursos = new ArrayList<Curso>();
		cursos.add(new Curso("Python", 45));
		cursos.add(new Curso("JavaScript", 150));
		cursos.add(new Curso("Java 8", 113));
		cursos.add(new Curso("C", 55));
		
//		Function<Curso, Integer> ordenaNumeroAlunois = Curso::getAlunos;
		
//		lambda
		cursos.sort(Comparator.comparing(c -> c.getAlunos()));
		
//		method reference
		cursos.sort(Comparator.comparing(Curso::getAlunos));
		
		
//		cursos.forEach(System.out::println);
		cursos.forEach(s -> System.out.println(s.getNome() + " contendo: " + s.getAlunos() + " alunos"));
		System.out.println();
		
		cursos.stream()
			.filter(c -> c.getAlunos() > 50)
			.forEach(s -> System.out.println(s.getNome() + " contendo: " + s.getAlunos() + " alunos"));
		System.out.println();
		
		Stream<String> nomes = cursos.stream().map(Curso::getNome);
		nomes.forEach(System.out::println);
		System.out.println();
		
		cursos.stream()
		   .filter(c -> c.getAlunos() > 50)	//pega cursos com alunos > 50
		   .map(c -> c.getAlunos())		//retorno um Integer com as quantidade dos alunos
		   .forEach(x -> System.out.println(x)); //printa o retorno da quantidade de alunos
		System.out.println();
//		trocando o lambda por mothod reference
		cursos.stream()
			.filter(c -> c.getAlunos() > 50)    //pega cursos com alunos > 50
			.map(Curso::getAlunos)				//retorno um Integer com as quantidade dos alunos
			.forEach(System.out::println);     //printa o retorno da quantidade de alunos
		System.out.println();
		
		
		  cursos.stream()
		   .filter(c -> c.getAlunos() > 50)			//pega cursos com alunos > 50
		   .findFirst()								//pega o primeiro
		   .ifPresent(c -> System.out.println(c.getNome()));  // se existir o curso com numero de > 50 prita, se nao fazz nada;
		System.out.println();

//		  fazer a media da quantidade de alunos e printar
		 OptionalDouble media =  cursos.stream()
		  	.mapToInt(c -> c.getAlunos())
		  	.average();
		 System.out.println(media.getAsDouble());
		 System.out.println();
//		 ou
		 cursos.stream()
		 	.mapToInt(c -> c.getAlunos())
		 	.average()
		 	.ifPresent(System.out::println);
		 
//		 ransformar esse Stream<Curso> filtrado em uma List<Curso>
		 List<Curso> listaCursos = cursos.stream()
		 	.filter(c -> c.getAlunos() > 50)	
		 	.collect(Collectors.toList());
		 listaCursos.forEach(c -> System.out.println(c.getNome()));
		 System.out.println();
		 
//		 tranformando em maplist
		 Map<String, Integer> mapCursos = cursos.stream()
			 .filter(c -> c.getAlunos() > 50)
			 .collect(Collectors.toMap(c -> c.getNome(), c-> c.getAlunos()));
		 System.out.println(mapCursos);
	}

}
