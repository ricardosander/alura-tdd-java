package br.com.caelum.leilao.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class LeilaoTest {

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		
		Usuario usuario = new Usuario("João");
		Usuario usuario2 = new Usuario("João");
		Leilao leilao = new Leilao("Leilãozão");
		
		leilao.propoe(new Lance(usuario, 100));
		leilao.propoe(new Lance(usuario2, 200));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(100, leilao.getLances().get(0).getValor(), 0);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Leilão João e Maria");
		
		leilao.propoe(new Lance(joao, 1000));
		leilao.propoe(new Lance(maria, 900));
		
		leilao.propoe(new Lance(joao, 800));
		leilao.propoe(new Lance(maria, 700));
		
		leilao.propoe(new Lance(joao, 600));
		leilao.propoe(new Lance(maria, 400));
		
		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(maria, 300));
		
		leilao.propoe(new Lance(joao, 200));
		leilao.propoe(new Lance(maria, 100));
		
		leilao.propoe(new Lance(joao, 50));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(100, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0);
	}
}
