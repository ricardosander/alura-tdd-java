package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;
	private double mediaLances = 0;
	private List<Lance> maiores;
	
	public void avalia(Leilao leilao) {
		
		for (Lance lance: leilao.getLances()) {
			
			if (this.maiorLance < lance.getValor()) {
				this.maiorLance = lance.getValor();
			}
			
			if (this.menorLance > lance.getValor()) {
				this.menorLance = lance.getValor();
			}
			this.mediaLances += lance.getValor();
		}
		
		if (leilao.getLances().size() > 0) {
			this.mediaLances /= leilao.getLances().size();
		}
		this.pegaOsMaiores(leilao);
	}
	
	private void pegaOsMaiores(Leilao leilao) {
		
		this.maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(this.maiores, new Comparator<Lance>() {
			
			public int compare(Lance lance1, Lance lance2) {
				if (lance1.getValor() < lance2.getValor()) return 1;
				if (lance1.getValor() > lance2.getValor()) return -1;
				return 0;
			}
		});
		this.maiores = this.maiores.subList(0, this.maiores.size() > 3 ? 3 : this.maiores.size());
	}
	
	public List<Lance> getTresMaiores() {
		return this.maiores;
	}
	
	public double getMaior() {
		return this.maiorLance;
	}
	
	public double getMenor() {
		return this.menorLance;
	}
	
	public double getMedia() {
		return mediaLances;
	}
}
