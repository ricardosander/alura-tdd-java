package br.com.caelum.leilao.servico;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.LeilaoBuilder;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before
	public void setUp() {

		this.leiloeiro = new Avaliador();
		
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
	}

	@Test
	public void testAvaliacaoOrdemCrescente() {

		Lance lanceMenor = new Lance(this.joao, 100);
		Lance lanceMedio = new Lance(this.jose, 200);
		Lance lanceMaior = new Lance(this.maria, 300);

		Leilao leilao = new LeilaoBuilder()
				.com("Leilão de testes.")
				.lance(lanceMenor)
				.lance(lanceMedio)
				.lance(lanceMaior)
				.controi();

		this.leiloeiro.avalia(leilao);

		double menor = 100.0;
		double maior = 300.0;
		double media = (100 + 200 + 300) / 3;

		Assert.assertEquals(menor, this.leiloeiro.getMenor(), 0);
		Assert.assertEquals(maior, this.leiloeiro.getMaior(), 0);
		Assert.assertEquals(media, this.leiloeiro.getMedia(), 0);
	}

	@Test
	public void testAvaliacaoOrdemAleatoria() {

		Leilao leilao  = new LeilaoBuilder()
			.com("leilão aleatório")
			.lance(new Lance(this.joao, 200))
			.lance(new Lance(this.jose, 450))
			.lance(new Lance(this.maria, 120))
			.lance(new Lance(this.joao, 700))
			.lance(new Lance(this.joao, 630))
			.lance(new Lance(this.maria, 230))
			.controi();

		this.leiloeiro.avalia(leilao);

		assertEquals(120, this.leiloeiro.getMenor(), 0);
		assertEquals(700, this.leiloeiro.getMaior(), 0);
	}

	@Test
	public void testAvaliacaoOrdemDecrescente() {

		Leilao leilao = new LeilaoBuilder()
				.com("Ordem decrescente.")
				.lance(new Lance(this.joao, 400))
				.lance(new Lance(this.maria, 300))
				.lance(new Lance(this.joao, 200))
				.lance(new Lance(this.maria, 100))
				.controi();
		
		this.leiloeiro.avalia(leilao);

		assertEquals(100, this.leiloeiro.getMenor(), 0);
		assertEquals(400, this.leiloeiro.getMaior(), 0);
	}

	@Test
	public void testApenasUmLance() {

		Leilao leilao = new LeilaoBuilder()
				.com("Leilão com proposta única.")
				.lance(new Lance(this.joao, 500.0))
				.controi();

		this.leiloeiro.avalia(leilao);

		assertEquals(500.0, this.leiloeiro.getMaior(), 0);
		assertEquals(500.0, this.leiloeiro.getMenor(), 0);
	}

	@Test
	public void testTresMaioresdeCinco() {

		Leilao leilao = new LeilaoBuilder()
				.com("Leilão para cinco.")
				.lance(new Lance(this.joao, 300))
				.lance(new Lance(this.maria, 200))
				.lance(new Lance(this.joao, 500))
				.lance(new Lance(this.maria, 150))
				.lance(new Lance(this.joao, 600))
				.controi();

		this.leiloeiro.avalia(leilao);

		assertEquals(3, this.leiloeiro.getTresMaiores().size());
		assertEquals(600, this.leiloeiro.getTresMaiores().get(0).getValor(), 0);
		assertEquals(500, this.leiloeiro.getTresMaiores().get(1).getValor(), 0);
		assertEquals(300, this.leiloeiro.getTresMaiores().get(2).getValor(), 0);
	}

	@Test
	public void testTresMaioresdeDois() {

		Leilao leilao = new LeilaoBuilder()
				.com("Leilão para cinco.")
				.lance(new Lance(this.joao, 300))
				.lance(new Lance(this.maria, 200))
				.controi();

		this.leiloeiro.avalia(leilao);

		assertEquals(2, this.leiloeiro.getTresMaiores().size());
		assertEquals(300, this.leiloeiro.getTresMaiores().get(0).getValor(), 0);
		assertEquals(200, this.leiloeiro.getTresMaiores().get(1).getValor(), 0);
	}

	@Test
	public void testTresMaioresdeNenhum() {

		Leilao leilao = new LeilaoBuilder()
				.com("Leilão para cinco.")
				.controi();

		this.leiloeiro.avalia(leilao);

		assertEquals(0, this.leiloeiro.getTresMaiores().size());
	}

}
