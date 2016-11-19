package br.com.caelum.matematica;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatematicaMalucaTest {

	@Test
	public void maiorQueLimiteSuperior() {
		
		int numero = 31;
		
		MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
		
		int esperado = numero * 4;
		assertEquals(esperado, matematicaMaluca.contaMaluca(numero));
	}
	
	@Test
	public void igualLimiteSuperior() {
		
		int numero = 30;
		
		MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
		
		int esperado = numero * 3;
		assertEquals(esperado, matematicaMaluca.contaMaluca(numero));
	}
	
	@Test
	public void entreLimiteSuperiorEInferior() {
		
		int numero = 20;
		
		MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
		
		int esperado = numero * 3;
		assertEquals(esperado, matematicaMaluca.contaMaluca(numero));
	}
	
	@Test
	public void poucoMaiorQueLimiteInferior() {
		
		int numero = 11;
		
		MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
		
		int esperado = numero * 3;
		assertEquals(esperado, matematicaMaluca.contaMaluca(numero));
	}
	
	@Test
	public void igualLimiteInferior() {
		
		int numero = 10;
		
		MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
		
		int esperado = numero * 2;
		assertEquals(esperado, matematicaMaluca.contaMaluca(numero));
	}
	
	@Test
	public void poucoMenorLimiteInferior() {
		
		int numero = 9;
		
		MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
		
		int esperado = numero * 2;
		assertEquals(esperado, matematicaMaluca.contaMaluca(numero));
	}
	
	@Test
	public void menorLimiteInferior() {
		
		int numero = 5;
		
		MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
		
		int esperado = numero * 2;
		assertEquals(esperado, matematicaMaluca.contaMaluca(numero));
	}
}
