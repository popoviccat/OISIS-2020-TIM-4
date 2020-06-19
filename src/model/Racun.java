package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Racun implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3215483934076629356L;
	private ArrayList<Stavka> prodatiLekovi;
	private String korisnickoImeApotekara;
	
	public Racun() {
		super();
		prodatiLekovi = new ArrayList<Stavka>();
	}
	
	public Racun(String korisnickoImeApotekara) {
		super();
		this.korisnickoImeApotekara = korisnickoImeApotekara;
		prodatiLekovi = new ArrayList<Stavka>();
	}

	public ArrayList<Stavka> getProdatiLekovi() {
		return prodatiLekovi;
	}
	
	public String getKorisnickoImeApotekara() {
		return korisnickoImeApotekara;
	}

	public void setKorisnickoImeApotekara(String korisnickoImeApotekara) {
		this.korisnickoImeApotekara = korisnickoImeApotekara;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prodatiLekovi == null) ? 0 : prodatiLekovi.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Racun other = (Racun) obj;
		if (prodatiLekovi == null) {
			if (other.prodatiLekovi != null)
				return false;
		} else if (!prodatiLekovi.equals(other.prodatiLekovi))
			return false;
		return true;
	}
}
