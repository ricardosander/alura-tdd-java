package br.com.caelum.leilao.servico;

import static br.com.caelum.leilao.desafio.LeilaoMacher.temUmLance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import static org.hamcrest.Matchers.*;

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
	
	@Test(expected=RuntimeException.class)
	public void leilaoSemPropostas() {
		
		Leilao leilao = new LeilaoBuilder()
				.com("Leilão sem lances")
				.controi();
		
		this.leiloeiro.avalia(leilao);
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

		assertThat(this.leiloeiro.getMenor(), equalTo(menor));
		assertThat(this.leiloeiro.getMaior(), equalTo(maior));
		assertThat(this.leiloeiro.getMedia(), equalTo(media));
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

		assertThat(this.leiloeiro.getMenor(), equalTo(120.0));
		assertThat(this.leiloeiro.getMaior(), equalTo(700.0));
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

		assertThat(this.leiloeiro.getMenor(), equalTo(100.0));
		assertThat(this.leiloeiro.getMaior(), equalTo(400.0));
	}

	@Test
	public void testApenasUmLance() {

		Leilao leilao = new LeilaoBuilder()
				.com("Leilão com proposta única.")
				.lance(new Lance(this.joao, 500.0))
				.controi();

		this.leiloeiro.avalia(leilao);

		assertThat(this.leiloeiro.getMaior(), equalTo(500.0));
		assertThat(this.leiloeiro.getMenor(), equalTo(500.0));
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
		List<Lance> maiores = this.leiloeiro.getTresMaiores();
		
		assertThat(maiores.size(), equalTo(3));
		assertThat(maiores, hasItems(
				new Lance(joao, 600),
				new Lance(joao, 500),
				new Lance(joao, 300)

		));
	}

	@Test
	public void testTresMaioresdeDois() {

		Leilao leilao = new LeilaoBuilder()
				.com("Leilão para cinco.")
				.lance(new Lance(this.joao, 300))
				.lance(new Lance(this.maria, 200))
				.controi();
		

		this.leiloeiro.avalia(leilao);
		
		List<Lance> maiores = this.leiloeiro.getTresMaiores();
		assertThat(leilao, temUmLance(new Lance(this.maria, 200)));
		assertThat(maiores.size(), equalTo(2));
		assertThat(maiores, hasItems(
				new Lance(joao, 300),
				new Lance(maria, 200)
		));
	}
	
}
