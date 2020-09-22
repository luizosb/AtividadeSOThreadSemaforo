package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPessoa;

public class Main {

	public static void main(String[] args) {
		int qtdPessoa = 4;
		int permissao = 1;
		int distanciaMaxima = 200;
		int i;
		
		Semaphore semaforo = new Semaphore(permissao);

		for (i = 0; i < qtdPessoa; i++) {
			Thread tCalculo = new ThreadPessoa("Pessoa " + (i+1), distanciaMaxima, semaforo);
			tCalculo.start();
		}

	}
}
