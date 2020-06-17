package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;

public class Recept implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2426243396907236602L;
	private int sifra;
	private String idLekara;
	private String jmbgPacijenta;
	private Date datumVreme;
	private HashMap<Lek,Integer> lekoviKolicina;
	
	public Recept() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Recept(int sifra, String idLekara, String jmbgPacijenta, Date datumVreme) {
		super();
		this.sifra = sifra;
		this.idLekara = idLekara;
		this.jmbgPacijenta = jmbgPacijenta;
		this.datumVreme = datumVreme;
		this.lekoviKolicina = new HashMap<Lek, Integer>();
		
	}
	public int getSifra() {
		return sifra;
	}
	public void setSifra(int sifra) {
		this.sifra = sifra;
	}
	public String getIdLekara() {
		return idLekara;
	}
	public void setIdLekara(String idLekara) {
		this.idLekara = idLekara;
	}
	public String getJmbgPacijenta() {
		return jmbgPacijenta;
	}
	public void setJmbgPacijenta(String jmbgPacijenta) {
		this.jmbgPacijenta = jmbgPacijenta;
	}
	public Date getDatumVreme() {
		return datumVreme;
	}
	public void setDatumVreme(Date datumVreme) {
		this.datumVreme = datumVreme;
	}
	public HashMap<Lek, Integer> getLekoviKolicina() {
		return lekoviKolicina;
	}
	
	public float getUkupnaCena() {
		float cena = 0;
		DecimalFormat twoDForm = new DecimalFormat("######.##");
		for(Lek l:this.lekoviKolicina.keySet()) {
			cena += l.getCena() * this.lekoviKolicina.get(l);
		}
		return BigDecimal.valueOf(cena).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
	}
		
	public void dodajLek (Lek lek, int kolicina) {
		this.lekoviKolicina.put(lek, kolicina);
	}
	
	public void obrisiLek (Lek lek) {
		this.lekoviKolicina.remove(lek);
	}
	
	@Override
	public String toString() {
		return "Recept [Sifra=" + sifra + ", IDLekara=" + idLekara + ", jmbg=" + jmbgPacijenta + ", Datum i vreme="
				+ datumVreme + ", Lekovi i kolicina=" + lekoviKolicina + ", Ukupna cena=" + getUkupnaCena() + "]";
	}
}
