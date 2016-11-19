package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		
		if (this.semLances() || this.podeLancar(lance)) {
			lances.add(lance);
		}
	}

	private boolean podeLancar(Lance lance) {
		return !isUltimo(lance.getUsuario()) && temQuantosLancamentos(lance.getUsuario()) <  5;
	}

	private int temQuantosLancamentos(Usuario usuario) {
		
		int total = 0;
		for (Lance l : this.getLances()) {
			
			if (l.getUsuario().equals(usuario)) total++;
		}
		return total;
	}

	private boolean isUltimo(Usuario usuario) {
		return ultimoLance().getUsuario().equals(usuario);
	}

	private Lance ultimoLance() {
		return this.getLances().get(this.getLances().size() - 1);
	}

	private boolean semLances() {
		return this.getLances().isEmpty();
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public void dobraLance(Usuario usuario) {
		
		Lance ultimoLance = ultimoLance(usuario);
		if (deveDobrar(ultimoLance)) {
			this.propoe(new Lance(usuario, ultimoLance.getValor() * 2));
		}
	}

	private boolean deveDobrar(Lance ultimoLance) {
		return !this.semLances() && ultimoLance != null;
	}

	private Lance ultimoLance(Usuario usuario) {
		
		Lance ultimoLance = null;
		for (Lance l : this.getLances()) {
			
			if (l.getUsuario().equals(usuario)) {
				ultimoLance = l;
			}
		}
		return ultimoLance;
	}
	
}
