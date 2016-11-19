package br.com.caelum.leilao.servico;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {
	
	@Test
	public void testAvaliacaoOrdemCrescente() {
		
		double menor = 100.0;
		double maior = 300.0;
		
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Lance lanceMenor = new Lance(joao, menor);
		Lance lanceMedio = new Lance(jose, 200);
		Lance lanceMaior = new Lance(maria, maior);
		
		Leilao leilao = new Leilao("Leilão de testes.");
		leilao.propoe(lanceMenor);
		leilao.propoe(lanceMedio);
		leilao.propoe(lanceMaior);
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		Assert.assertEquals(menor, avaliador.getMenor(), 0);
		Assert.assertEquals(maior, avaliador.getMaior(), 0);
	}

}
