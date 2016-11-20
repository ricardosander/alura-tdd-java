package br.com.caelum.leilao.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class LanceTest {

	@Test(expected=IllegalArgumentException.class)
	public void naoAceitaValorNegativo() {
		
		new Lance(new Usuario("Caloteiro"), -100);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoAceitaValorZerado() {
		
		new Lance(new Usuario("Caloteiro"), 0);
	}
	
	@Test
	public void aceitaValorPositivo() {
		
		Lance lance = new Lance(new Usuario("Honesto"), 100);
		assertEquals(100, lance.getValor(), 0);
	}
}
