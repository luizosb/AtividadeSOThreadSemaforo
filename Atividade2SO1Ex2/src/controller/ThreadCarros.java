package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarros extends Thread{

	private int distanciaTotal;
	private int distanciaPercorrida;
	private Semaphore semaforo;
	private String carro;
	private int acelerando;
	
	public ThreadCarros(String carro, int distanciaMaxima, Semaphore semaforo) {
		super(carro);
		this.carro = carro;
		this.distanciaTotal = distanciaMaxima;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		while (distanciaPercorrida < distanciaTotal) {
			carroAndando();
			try {
				semaforo.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				carroAtravessandoCruzamento();
				semaforo.release();
				carroTerminouDeAtravessar();
			}
		}
	}

	private void carroAndando() {
		acelerando = (int) (Math.random()*(10 - 2) + 2);
		while(distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += acelerando;
			if(distanciaPercorrida > distanciaTotal) {
				distanciaPercorrida = distanciaTotal;
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mostrarSituaçãoDoCarro();
		}
		System.out.println("\nO " + carro + " chegou no cruzamento. Aguardando semáforo abrir.");
	}

	private void carroAtravessandoCruzamento() {
		System.out.println("\nSinal verde. O " + carro + " está atravessando o cruzamento.");
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void carroTerminouDeAtravessar() {
		System.out.println("\nO " + carro + " atravessou");
		
	}
	
	private void mostrarSituaçãoDoCarro() {
		System.out.println("O " + carro + " andou " + acelerando + " metros.");
	}
	
}
