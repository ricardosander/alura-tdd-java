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
	
	@Test 
	public void deveDobrarLanceUsuarioDiferente() {
		
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Dobrador 1");
		
		leilao.propoe(new Lance(joao, 100));
		leilao.propoe(new Lance(maria, 150));
		leilao.dobraLance(joao);
		
		assertEquals(3, leilao.getLances().size());
		assertEquals(100, leilao.getLances().get(0).getValor(), 0);
		assertEquals(joao, leilao.getLances().get(0).getUsuario());
		assertEquals(150, leilao.getLances().get(1).getValor(), 0);
		assertEquals(maria, leilao.getLances().get(1).getUsuario());
		assertEquals(200, leilao.getLances().get(2).getValor(), 0);
		assertEquals(joao, leilao.getLances().get(2).getUsuario());
	}
	
	@Test 
	public void naoDeveDobrarLanceMesmoUsuario() {
		
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Dobrador 2");
		
		leilao.propoe(new Lance(joao, 100));
		leilao.dobraLance(joao);
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(100, leilao.getLances().get(0).getValor(), 0);
		assertEquals(joao, leilao.getLances().get(0).getUsuario());
	}
	
	@Test 
	public void naoDeveDobrarSemLances() {
		
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Dobrador 2");
		
		leilao.dobraLance(joao);
		
		assertEquals(0, leilao.getLances().size());
	}
	
	@Test
	public void naoDeveDobrarMaisDoQue5LancesDeUmMesmoUsuario() {
		
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
		
		leilao.dobraLance(joao);
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(100, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0);
	}
	
	@Test
	public void naoDeveDobraUsuarioNaoLancou() {
		
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Nao Dobra quem não foi ainda.");
		
		leilao.propoe(new Lance(joao, 100));
		leilao.dobraLance(maria);;
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(100, leilao.getLances().get(0).getValor(), 0);
	}
}
