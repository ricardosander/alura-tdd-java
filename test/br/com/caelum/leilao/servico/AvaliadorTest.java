package br.com.caelum.leilao.servico;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {
	
	@Test
	public void testAvaliacaoOrdemCrescente() {
		
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Lance lanceMenor = new Lance(joao, 100);
		Lance lanceMedio = new Lance(jose, 200);
		Lance lanceMaior = new Lance(maria, 300);
		
		Leilao leilao = new Leilao("Leilão de testes.");
		leilao.propoe(lanceMenor);
		leilao.propoe(lanceMedio);
		leilao.propoe(lanceMaior);
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
	
		double menor = 100.0;
		double maior = 300.0;
		double media = (100 + 200 + 300) / 3;
		
		Assert.assertEquals(menor, avaliador.getMenor(), 0);
		Assert.assertEquals(maior, avaliador.getMaior(), 0);
		Assert.assertEquals(media, avaliador.getMedia(), 0);
	}
	
	@Test
	public void testAvaliacaoOrdemAleatoria() {
		
		Usuario usuario1 = new Usuario("Usuário 1");
		Usuario usuario2 = new Usuario("Usuário 2");
		Usuario usuario3 = new Usuario("Usuário 3");
		Usuario usuario4 = new Usuario("Usuário 4");
		Usuario usuario5 = new Usuario("Usuário 5");
		Usuario usuario6 = new Usuario("Usuário 6");
		
		Lance lance1 = new Lance(usuario1, 200);
		Lance lance2 = new Lance(usuario2, 450);
		Lance lance3 = new Lance(usuario3, 120);
		Lance lance4 = new Lance(usuario4, 700);
		Lance lance5 = new Lance(usuario5, 630);
		Lance lance6 = new Lance(usuario6, 230);
		
		Leilao leilao = new Leilao("leilão aleatório");
		leilao.propoe(lance1);
		leilao.propoe(lance2);
		leilao.propoe(lance3);
		leilao.propoe(lance4);
		leilao.propoe(lance5);
		leilao.propoe(lance6);
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(120, avaliador.getMenor(), 0);
		assertEquals(700, avaliador.getMaior(), 0);
	}
	
	@Test
	public void testAvaliacaoOrdemDecrescente() {
		
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Lance lance1 = new Lance(joao, 400);
		Lance lance2 = new Lance(maria, 300);
		Lance lance3 = new Lance(joao, 200);
		Lance lance4 = new Lance(maria, 100);
		
		Leilao leilao = new Leilao("Ordem decrescente.");
		leilao.propoe(lance1);
		leilao.propoe(lance2);
		leilao.propoe(lance3);
		leilao.propoe(lance4);
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(100, avaliador.getMenor(), 0);
		assertEquals(400, avaliador.getMaior(), 0);
	}
	
	@Test
	public void testApenasUmLance() {
		
		Usuario usuario = new Usuario("Um único usuário");
		Lance lance = new Lance(usuario, 500.0);
		Leilao leilao = new Leilao("Leilão com proposta única.");
		leilao.propoe(lance);
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(500.0, avaliador.getMaior(), 0);
		assertEquals(500.0, avaliador.getMenor(), 0);
	}
	
	@Test
	public void testTresMaioresdeCinco() {
		
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Leilão para cinco.");
		leilao.propoe(new Lance(joao, 300));
		leilao.propoe(new Lance(maria, 200));
		leilao.propoe(new Lance(joao, 500));
		leilao.propoe(new Lance(maria, 150));
		leilao.propoe(new Lance(maria, 600));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(3, avaliador.getTresMaiores().size());
		assertEquals(600, avaliador.getTresMaiores().get(0).getValor(), 0);
		assertEquals(500, avaliador.getTresMaiores().get(1).getValor(), 0);
		assertEquals(300, avaliador.getTresMaiores().get(2).getValor(), 0);
	}
	
	@Test
	public void testTresMaioresdeDois() {
		
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Leilão para cinco.");
		leilao.propoe(new Lance(joao, 300));
		leilao.propoe(new Lance(maria, 200));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(2, avaliador.getTresMaiores().size());
		assertEquals(300, avaliador.getTresMaiores().get(0).getValor(), 0);
		assertEquals(200, avaliador.getTresMaiores().get(1).getValor(), 0);
	}
	

	@Test
	public void testTresMaioresdeNenhum() {
		
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Leilão para cinco.");
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(0, avaliador.getTresMaiores().size());
	}

}
