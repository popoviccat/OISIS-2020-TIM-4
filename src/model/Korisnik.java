package model;

import java.io.Serializable;
import java.util.Date;


public class Korisnik implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9207254613509507944L;
	private String korisnickoIme;
	private String lozinka;
	private String ime;
	private String prezime;
	private TipKorisnika tipKorisnika;
	private boolean logickiObrisan;
	
	public Korisnik() {
		super();
	}
	
	public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika, boolean logickiObrisan) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.tipKorisnika = tipKorisnika;
		this.logickiObrisan = false;
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public TipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}
	public void setTipKorisnika(TipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}
	
	public boolean getLogickiObrisan() {
		return logickiObrisan;
	}
	public void setLogickiObrisan(boolean logickiObrisan) {
		this.logickiObrisan = logickiObrisan;
	}
	
	
	@Override
	public String toString() {
		return "Korisnik [Korisnicko Ime=" + korisnickoIme + ", Lozinka=" + lozinka + ", Ime=" + ime + ", Prezime="
				+ prezime + ", Tip Korisnika=" + tipKorisnika + "]";
	}
	
}
