package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCarros;

public class Cruzamento {

	public static void main(String[] args) {
		int qtdCarros = 4;
		int permissao = 1;
		int distanciaMaxima = 20;
		int i;
		String carro;
		
		Semaphore semaforo = new Semaphore(permissao);

		for (i = 0; i < qtdCarros; i++) {
			if(i == 0) {
				carro = "Carro Norte-Sul";
			} else if(i==1) {
				carro = "Carro Sul-Norte";
			} else if(i==2) {
				carro = "Carro Leste-Oeste";
			} else {
				carro = "Carro Oeste-Leste";
			}
			Thread tCalculo = new ThreadCarros(carro, distanciaMaxima, semaforo);
			tCalculo.start();
		}

	}

}
