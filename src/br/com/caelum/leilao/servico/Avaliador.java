package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;
	
	public void avalia(Leilao leilao) {
		
		for (Lance lance: leilao.getLances()) {
			
			if (this.maiorLance < lance.getValor()) {
				this.maiorLance = lance.getValor();
			}
			
			if (this.menorLance > lance.getValor()) {
				this.menorLance = lance.getValor();
			}
		}
	}
	
	public double getMaior() {
		return this.maiorLance;
	}
	
	public double getMenor() {
		return this.menorLance;
	}
}
