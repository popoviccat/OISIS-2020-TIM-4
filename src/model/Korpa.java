package model;

import java.util.HashMap;

public class Korpa {
	private HashMap<Lek, Integer> lekoviUKorpi;

	public Korpa() {
		super();
		lekoviUKorpi = new HashMap<Lek, Integer>();
	}
	
	public HashMap<Lek, Integer> getLekoviUKorpi() {
		return lekoviUKorpi;
	}
	
	public float getCenaSvihLekovaUKorpi() {
		float cena = 0;
		for(Lek l : lekoviUKorpi.keySet()) {
			cena += l.getCena() * lekoviUKorpi.get(l);
		}
		return cena;
	}
	
	public void dodajLekUKorpu(Lek l, int kol) {
		if (lekoviUKorpi.containsKey(l)) {
			lekoviUKorpi.put(l, lekoviUKorpi.get(l) + kol);
		} else {
			lekoviUKorpi.put(l, kol);
		}
	}
}
