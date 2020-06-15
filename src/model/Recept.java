package model;

import java.io.Serializable;
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
		for(Lek l:this.lekoviKolicina.keySet()) {
			cena += l.getCena() * this.lekoviKolicina.get(l);
		}
		return cena;
	}
		
	public void dodajLek (Lek lek, int kolicina) {
		this.lekoviKolicina.put(lek, kolicina);
	}
	
	public void obrisiLek (Lek lek) {
		this.lekoviKolicina.remove(lek);
	}
}
