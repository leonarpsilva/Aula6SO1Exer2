package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadCruzamento extends Thread {

	private int idCarro;
	private Semaphore semaforo;
	private String[] sentido = { "rua de baixo para rua de cima", "rua de cima para rua de baixo",
			"rua da esquerda para a rua da direita", "rua da direita para a rua da esquerda" };
	private String[] sentidos = { "rua de cima", "rua de baixo", "rua da direita", "rua da esquerda" };
	private static int sentido1 = 0;
	private static int posicao = 1;

	public ThreadCruzamento(int idCarro, Semaphore semaforo) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		try {
			semaforo.acquire();
			cruzamento();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			posicao++;
			semaforo.release();
		}
	}

	private void cruzamento() {
		System.out.println("O carro " + idCarro + " est� aguardando para cruzar");
		Deslocamento();
		System.out.println("O carro " + idCarro + " cruzou a rua e chegou na " + sentidos[sentido1]);
		sentido1++;
	}

	private void Deslocamento() {
		System.out.println("O carro " + idCarro + " foi o " + posicao + "� a cruzar na " + sentido[sentido1]);
		int tempo = getRandomTime(1000, 1);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getRandomTime(int maximo, int minimo) {
		Random rd = new Random();
		return rd.nextInt(maximo - minimo + 1) + minimo;
	}

}