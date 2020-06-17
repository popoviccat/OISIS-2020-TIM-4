package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Prodaja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3215483934076629356L;
	private ArrayList<Stavka> prodatiLekovi;
	
	public Prodaja() {
		super();
		prodatiLekovi = new ArrayList<Stavka>();
	}

	public ArrayList<Stavka> getProdatiLekovi() {
		return prodatiLekovi;
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
		Prodaja other = (Prodaja) obj;
		if (prodatiLekovi == null) {
			if (other.prodatiLekovi != null)
				return false;
		} else if (!prodatiLekovi.equals(other.prodatiLekovi))
			return false;
		return true;
	}
}
