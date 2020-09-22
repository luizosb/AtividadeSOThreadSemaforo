package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCarroFormula1;

public class Interlagos {

	public static void main(String[] args) {
		int qtdCarros = 14;
		int permissao = 5;
		int distanciaMaxima = 2000;
		int i;
		Semaphore semaforo = new Semaphore(permissao);
		String formula1;

		for (i = 0; i < qtdCarros; i++) {
			if (i == 0 || i == 1) {
				formula1 = "Ferrari";
			} else if (i == 2 || i == 3) {
				formula1 = "Mercedez";
			} else if (i == 4 || i == 5) {
				formula1 = "RedBull";
			} else if (i == 6 || i == 7) {
				formula1 = "Renault";
			} else if (i == 8 || i == 9) {
				formula1 = "McLaren";
			} else if (i == 10 || i == 11) {
				formula1 = "Williams";
			} else {
				formula1 = "Haas";
			}
			Thread tCalculo = new ThreadCarroFormula1(formula1 + "-" + (i+1), distanciaMaxima, semaforo);
			tCalculo.start();
		}

	}

}
