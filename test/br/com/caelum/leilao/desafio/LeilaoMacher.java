package br.com.caelum.leilao.desafio;

import org.hamcrest.*;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class LeilaoMacher extends TypeSafeMatcher<Leilao> {

	private final Lance lance;

	public LeilaoMacher(Lance lance) {
		this.lance = lance;
	}
	
	public void describeTo(Description descricao) {
		descricao.appendText("lailão com lance " + this.lance.getValor());
	}

	@Override
	protected boolean matchesSafely(Leilao leilao) {
		return leilao.getLances().contains(lance);
	}

	public static LeilaoMacher temUmLance(Lance lance) {
		return new LeilaoMacher(lance);
	}

}