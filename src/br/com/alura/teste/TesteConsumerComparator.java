package br.com.alura.teste;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class TesteConsumerComparator {

	public static void main(String[] args) {

		List<String> nomes = new ArrayList<>();
		nomes.add("Aula alura java 8");
		nomes.add("Vitor");
		nomes.add("Jogar ate as 22:30");

		System.out.println(nomes);

		System.out.println();
		for (String nome : nomes) {
			System.out.println(nome);
		}

		nomes.sort(null);
		System.out.println();
		nomes.forEach(nome -> System.out.println(nome));

//		esse é
		Comparator<String> comparaTam = new comparadorTamanha();
		nomes.sort(comparaTam);
		
//		equivalente a esse
		nomes.sort(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o1.length(), o2.length());
			}
		});
		
//		e equivalente a esse 
		nomes.sort((o1, o2) -> Integer.compare(o1.length(), o2.length()));
		System.out.println();
		
//		e equivalente a esse 
		nomes.sort(Comparator.comparing(s -> s.length()));
		
//		e equivalente a esse 
		Function<String, Integer> funcao = String::length;
		nomes.sort(Comparator.comparing(funcao));
		
//		e equivalente a esse 
		nomes.sort(Comparator.comparing(String::length));
		nomes.sort(String.CASE_INSENSITIVE_ORDER);
		
		
		
		Consumer<String> imprime = new imprimeNome();
		nomes.forEach(imprime);
		
		System.out.println();
		nomes.forEach(System.out::println);
		System.out.println();

		/**
		 * exercicico de convercao de lambda
		 */
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Executando um Runnable");
			}

		}).start();
		/**
		 * resposta
		 */
		new Thread(() -> System.out.println("Executando um Runnable")).start();
	}
}

class imprimeNome implements Consumer<String> {

	@Override
	public void accept(String t) {
		System.out.println(t);

	}

}

class comparadorTamanha implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		if (o1.length() > o2.length())
			return -1;
		else if (o1.length() < o2.length())
			return 1;
		else
			return 0;
	}

}
