package br.com.caelum.leilao.desafio;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnoBissextoTest {

	@Test
	public void devemSerBissextosMultiplisDe400() {
		
		AnoBissexto anoBissexto = new AnoBissexto();
		assertTrue(anoBissexto.ehBissexto(400));
		assertTrue(anoBissexto.ehBissexto(800));
		assertTrue(anoBissexto.ehBissexto(1200));
		assertTrue(anoBissexto.ehBissexto(1600));
		assertTrue(anoBissexto.ehBissexto(2000));
		assertTrue(anoBissexto.ehBissexto(2400));
	}
	
	@Test 
	public void devemSerBissextoDe4Em4MasNaoDe100Em100() {
		
		AnoBissexto anoBissexto = new AnoBissexto();
		
		assertTrue(anoBissexto.ehBissexto(4));
		assertTrue(anoBissexto.ehBissexto(8));
		assertTrue(anoBissexto.ehBissexto(12));
		assertTrue(anoBissexto.ehBissexto(16));
		assertTrue(anoBissexto.ehBissexto(20));
		assertTrue(anoBissexto.ehBissexto(24));
		
		assertTrue(anoBissexto.ehBissexto(92));
		assertTrue(anoBissexto.ehBissexto(96));
		assertFalse(anoBissexto.ehBissexto(100));
		assertTrue(anoBissexto.ehBissexto(104));
		assertTrue(anoBissexto.ehBissexto(108));
		assertTrue(anoBissexto.ehBissexto(112));

		assertTrue(anoBissexto.ehBissexto(992));
		assertTrue(anoBissexto.ehBissexto(996));
		assertFalse(anoBissexto.ehBissexto(1000));
		assertTrue(anoBissexto.ehBissexto(1004));
		assertTrue(anoBissexto.ehBissexto(1008));
		assertTrue(anoBissexto.ehBissexto(1012));

		assertTrue(anoBissexto.ehBissexto(1992));
		assertTrue(anoBissexto.ehBissexto(1996));
		assertTrue(anoBissexto.ehBissexto(1996));
		assertTrue(anoBissexto.ehBissexto(2000));
		assertTrue(anoBissexto.ehBissexto(2004));
		assertTrue(anoBissexto.ehBissexto(2008));
	}
	
	@Test
	public void naoDevemSerBisextoMultiplosDe2NaoDe4() {
	
		AnoBissexto anoBissexto = new AnoBissexto();
		
		assertFalse(anoBissexto.ehBissexto(2));
		assertFalse(anoBissexto.ehBissexto(6));
		assertFalse(anoBissexto.ehBissexto(10));
		assertFalse(anoBissexto.ehBissexto(14));
		assertFalse(anoBissexto.ehBissexto(18));
		assertFalse(anoBissexto.ehBissexto(22));
		
		assertFalse(anoBissexto.ehBissexto(90));
		assertFalse(anoBissexto.ehBissexto(94));
		assertFalse(anoBissexto.ehBissexto(98));
		assertFalse(anoBissexto.ehBissexto(102));
		assertFalse(anoBissexto.ehBissexto(106));
		assertFalse(anoBissexto.ehBissexto(110));

		assertFalse(anoBissexto.ehBissexto(990));
		assertFalse(anoBissexto.ehBissexto(994));
		assertFalse(anoBissexto.ehBissexto(998));
		assertFalse(anoBissexto.ehBissexto(1002));
		assertFalse(anoBissexto.ehBissexto(1006));
		assertFalse(anoBissexto.ehBissexto(1010));

		assertFalse(anoBissexto.ehBissexto(1990));
		assertFalse(anoBissexto.ehBissexto(1994));
		assertFalse(anoBissexto.ehBissexto(1998));
		assertFalse(anoBissexto.ehBissexto(2002));
		assertFalse(anoBissexto.ehBissexto(2006));
	}
}
