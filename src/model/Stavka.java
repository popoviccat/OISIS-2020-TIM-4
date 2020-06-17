package model;

import java.io.Serializable;

public class Stavka implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -536415371138067005L;
	private Lek lek;
	private int kolicina;
	private String korisnickoImeApotekara;
	
	public Stavka() {
		super();
	}

	public Stavka(Lek lek, int kolicina, String korisnickoImeApotekara) {
		super();
		this.lek = lek;
		this.kolicina = kolicina;
		this.korisnickoImeApotekara = korisnickoImeApotekara;
	}

	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public String getKorisnickoImeApotekara() {
		return korisnickoImeApotekara;
	}

	public void setKorisnickoImeApotekara(String korisnickoImeApotekara) {
		this.korisnickoImeApotekara = korisnickoImeApotekara;
	}
}
