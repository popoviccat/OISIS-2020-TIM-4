package model;

import java.io.Serializable;

public class Stavka implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -536415371138067005L;
	private Lek lek;
	private int kolicina;
	
	public Stavka() {
		super();
	}

	public Stavka(Lek lek, int kolicina) {
		super();
		this.lek = lek;
		this.kolicina = kolicina;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lek == null) ? 0 : lek.hashCode());
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
		Stavka other = (Stavka) obj;
		if (lek == null) {
			if (other.lek != null)
				return false;
		} else if (!lek.equals(other.lek))
			return false;
		return true;
	}
}
