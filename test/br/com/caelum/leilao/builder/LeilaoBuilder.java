package br.com.caelum.leilao.builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class LeilaoBuilder {
	
	private Leilao leilao;

	public LeilaoBuilder com(String conteudo) {
		this.leilao = new Leilao(conteudo);
		return this;
	}
	
	public LeilaoBuilder lance(Lance lance) {
		this.leilao.propoe(lance);
		return this;
	}
	
	public Leilao controi() {
		return this.leilao;
	}

}
