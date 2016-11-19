package br.com.caelum.leilao.desafio;

import org.junit.Assert;
import org.junit.Test;

public class PalindromoTest {

	@Test
	public void test() {
		
		String palavra1 = "Socorram-me subi no onibus em Marrocos";
		String palavra2 = "Anotaram a data da maratona";
		String palavra3 = "Frase aleatória";
		String palavra4 = "Deve dar falso também";
		
		Palindromo palindromo = new Palindromo();
		
		Assert.assertTrue(palindromo.ehPalindromo(palavra1));
		Assert.assertTrue(palindromo.ehPalindromo(palavra2));
		Assert.assertFalse(palindromo.ehPalindromo(palavra3));
		Assert.assertFalse(palindromo.ehPalindromo(palavra4));
	}
}
