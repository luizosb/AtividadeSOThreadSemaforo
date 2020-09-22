package controller;

import java.util.concurrent.Semaphore;

@SuppressWarnings("unused")
public class ThreadCarroFormula1 extends Thread {

	private int acelerando;
	private int distanciaPercorridaDoBox;
	private int distanciaTotalBox = 300;
	private int distanciaPercorridaPista;
	private int distanciaTotalPista;
	private final static int velocidadeMaximaNoBox = 100;
	private final static int velocidadeMinimaNoBox = 70;
	private final static int velocidadeMaximaNaPista = 310;
	private final static int velocidadeMinimaNaPista = 100;
	private long tempoDeVolta = (long) (Math.random() * ((3 - 1) + 1) * 1000);
	private String formula1;
	private long menorTempoVolta = 0;
	private static int colocacao = 0;
	private Semaphore semaforoPista;

	public ThreadCarroFormula1(String formula1, int distanciaMaxima, Semaphore semaforo) {
		this.formula1 = formula1;
		this.distanciaTotalPista = distanciaMaxima;
		this.semaforoPista = semaforo;
	}

	@Override
	public void run() {
		while (distanciaPercorridaDoBox < distanciaTotalBox) {
			formula1EntrandoNaPista();
			try {
				semaforoPista.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				formula1AcelerandoNaPista();
				semaforoPista.release();
			}
		}
		colocacaoFormula1();

	}

	private void formula1EntrandoNaPista() {
		int acelerandoNoBox = (int) (Math.random() * (velocidadeMaximaNoBox - velocidadeMinimaNoBox)
				  + velocidadeMinimaNoBox);
		while (distanciaPercorridaDoBox < distanciaTotalBox) {
			distanciaPercorridaDoBox += acelerandoNoBox;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\nO " + formula1 + " está aguardando para entrar na pista");
	}

	private void formula1AcelerandoNaPista() {
		System.out.println("\nO " + formula1 + " entrou na pista.");
			acelerando = (int) (Math.random() * (velocidadeMaximaNaPista - velocidadeMinimaNaPista)
					+ velocidadeMinimaNaPista);
			while (distanciaPercorridaPista < distanciaTotalPista) {
				distanciaPercorridaPista += acelerando;
				try {
					sleep(tempoDeVolta);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				menorTempoVolta = 5000;
				if (tempoDeVolta < menorTempoVolta) {
					menorTempoVolta = tempoDeVolta;
				}
			}
			formula1SaindoDaPista();
	}

	private void formula1SaindoDaPista() {
		System.out.println("O " + formula1 + " entrou no box.");
	}

	private void colocacaoFormula1() {
		colocacao += 1;
		System.out.println("\n" + colocacao + "-" + formula1 + ", tempo: " + menorTempoVolta);
	}
}
