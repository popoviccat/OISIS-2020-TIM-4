package model;

public class Lek {
	private String sifra;
	private String ime;
	private String proizvodjac;
	private boolean izdajeSeNaRecept;
	private float cena;
	
	public Lek() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lek(String sifra, String ime, String proizvodjac, boolean izdajeSeNaRecept, float cena) {
		super();
		this.sifra = sifra;
		this.ime = ime;
		this.proizvodjac = proizvodjac;
		this.izdajeSeNaRecept = izdajeSeNaRecept;
		this.cena = cena;
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

	public boolean isIzdajeSeNaRecept() {
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
	
	
	
}
