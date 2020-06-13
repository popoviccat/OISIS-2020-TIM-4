package model;

import java.io.Serializable;

public class Lek implements Serializable{
	private String sifra;
	private String ime;
	private String proizvodjac;
	private boolean izdajeSeNaRecept;
	private float cena;
	private boolean logickiObrisan;
	
	public Lek() {
		super();
	}

	public Lek(String sifra, String ime, String proizvodjac, boolean izdajeSeNaRecept, float cena, boolean logickiObrisan) {
		super();
		this.sifra = sifra;
		this.ime = ime;
		this.proizvodjac = proizvodjac;
		this.izdajeSeNaRecept = izdajeSeNaRecept;
		this.cena = cena;
		this.logickiObrisan = false;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public boolean getIzdajeSeNaRecept() {
		return izdajeSeNaRecept;
	}

	public void setIzdajeSeNaRecept(boolean izdajeSeNaRecept) {
		this.izdajeSeNaRecept = izdajeSeNaRecept;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}
	
	public boolean getLogickiObrisan() {
		return logickiObrisan;
	}
	public void setLogickiObrisan(boolean logickiObrisan) {
		this.logickiObrisan = logickiObrisan;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sifra == null) ? 0 : sifra.hashCode());
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
		Lek other = (Lek) obj;
		if (sifra == null) {
			if (other.sifra != null)
				return false;
		} else if (!sifra.equals(other.sifra))
			return false;
		return true;
	}
	
/*	@Override
	public String toString() {
		return "Korisnik [Korisnicko Ime=" + korisnickoIme + ", Lozinka=" + lozinka + ", Ime=" + ime + ", Prezime="
				+ prezime + ", Tip Korisnika=" + tipKorisnika + "]";
	}*/
	
}
