package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Prodaja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3215483934076629356L;
	private ArrayList<Racun> kupovine;
	
	public Prodaja() {
		super();
		kupovine = new ArrayList<Racun>();
	}

	public ArrayList<Racun> getKupovine() {
		return kupovine;
	}
	
	public ArrayList<Stavka> getListaSvihProdatihStavki() {
		ArrayList<Stavka> stavke = new ArrayList<>();
		for (Racun r : kupovine) {
			for (Stavka s : r.getProdatiLekovi()) {
				if (stavke.contains(s)) {
					int staraKolicina = stavke.get(stavke.indexOf(s)).getKolicina();
					s.setKolicina(staraKolicina + s.getKolicina());
					stavke.set(stavke.indexOf(s), s);
				} else {
					stavke.add(s);
				}
			}
		}
		
		return stavke;
	}
	
	public ArrayList<Stavka> getListaSvihProdatihStavkiOdabranogApotekara(String apotekar) {
		ArrayList<Stavka> stavke = new ArrayList<>();
		for (Racun r : kupovine) {
			if (r.getKorisnickoImeApotekara().equals(apotekar)) {
				for (Stavka s : r.getProdatiLekovi()) {
					if (stavke.contains(s)) {
						int staraKolicina = stavke.get(stavke.indexOf(s)).getKolicina();
						s.setKolicina(staraKolicina + s.getKolicina());
						stavke.set(stavke.indexOf(s), s);
					} else {
						stavke.add(s);
					}
				}				
			}
		}
		
		return stavke;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kupovine == null) ? 0 : kupovine.hashCode());
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
		if (kupovine == null) {
			if (other.kupovine != null)
				return false;
		} else if (!kupovine.equals(other.kupovine))
			return false;
		return true;
	}
}
