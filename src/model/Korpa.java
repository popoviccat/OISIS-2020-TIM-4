package model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
		DecimalFormat twoDForm = new DecimalFormat("######.##");
		for(Lek l : lekoviUKorpi.keySet()) {
			cena += l.getCena() * lekoviUKorpi.get(l);
		}
		return BigDecimal.valueOf(cena).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	public void dodajLekUKorpu(Lek l, int kol) {
		if (lekoviUKorpi.containsKey(l)) {
			lekoviUKorpi.put(l, lekoviUKorpi.get(l) + kol);
		} else {
			lekoviUKorpi.put(l, kol);
		}
	}
}
