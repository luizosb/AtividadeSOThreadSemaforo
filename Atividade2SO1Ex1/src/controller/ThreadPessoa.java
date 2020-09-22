package controller;

import java.util.concurrent.Semaphore;

public class ThreadPessoa extends Thread {

	private String nome;
	private int distanciaPercorrida;
	private int distanciaTotal;
	private long tempoAbrirPorta = (long) (Math.random() * (3000 - 1000) + 1000);
	private final static int velocidadeMaxima = 7;
	private final static int velocidadeMinima = 4;
	private int andar;
	private Semaphore semaforo;

	public ThreadPessoa(String nome, int distanciaTotal, Semaphore semaforo) {
		super(nome);
		this.distanciaTotal = distanciaTotal;
		this.nome = nome;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		while (distanciaPercorrida < distanciaTotal) {
			pessoaAndando();
//			-----------Inicio Seção Critica
			try {
				semaforo.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				pessoaAbrindoPorta();
				semaforo.release();
//			-----------Termino Seção Crítica
				pessoaSaindoDaPorta();
			}
		}
	}

	private void pessoaAndando() {
		andar = (int) (Math.random() * (velocidadeMaxima - velocidadeMinima) + velocidadeMinima);
		while (distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += andar;
			if (distanciaPercorrida > distanciaTotal) {
				distanciaPercorrida = distanciaTotal;
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mostrarDistanciaPercorrida();
		}
		System.out.println("\nA " + nome + " chegou na porta.");

	}

	private void mostrarDistanciaPercorrida() {
		System.out.println("A " + nome + " andou " + andar + "m e percorreu " + distanciaPercorrida + " metros.");
	}

	private void pessoaAbrindoPorta() {
		System.out.println("\nA " + nome + " abriu a porta.");
		try {
			sleep(tempoAbrirPorta);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void pessoaSaindoDaPorta() {
		System.out.println("\nA " + nome + " saiu da porta.");
	}

}
