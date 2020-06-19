package controlers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Korisnik;
import model.Lek;
import model.Prodaja;
import model.Racun;
import model.Recept;

public class writeToFile {
	
	public static void writeToFileKor(Korisnik noviKorisnik) throws IOException, ClassNotFoundException {
		ArrayList<Korisnik> ucitaniKorisnici = readFromFile.readFromFileKor();
		ucitaniKorisnici.add(noviKorisnik);
		  
		File f = new File("Korisnici.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		try {
			oos.writeObject(ucitaniKorisnici);
		} finally {
			oos.close();
		}
	}
	
	public static void updateDatabaseKor(ArrayList<Korisnik> zapisiKorisnike) throws ClassNotFoundException, IOException {
		/*ArrayList<Korisnik> zapisiKorisnike1 = new ArrayList<Korisnik>();
		Korisnik kor1 = new Korisnik("admin", "admin", "Administrator", "Adminović", TipKorisnika.ADMINISTRATOR, false);
		zapisiKorisnike1.add(kor1);*/		// obnavlja bazu
		
		File f = new File("Korisnici.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		try {
			oos.writeObject(zapisiKorisnike);
		} finally {
			oos.close();
		}
	}
	
	public static void writeToFileLek(Lek noviLek) throws IOException, ClassNotFoundException {
		ArrayList<Lek> ucitaniLekovi = readFromFile.readFromFileLek();
		ucitaniLekovi.add(noviLek);
		  
		File f = new File("Lekovi.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		try {
			oos.writeObject(ucitaniLekovi);
		} finally {
			oos.close();
		}
	}
	
	public static void updateDatabaseLek(ArrayList<Lek> Lekovi) throws ClassNotFoundException, IOException {
		/*ArrayList<Lek> zapisiLekovi = new ArrayList<Lek>();
		Lek lek1 = new Lek("N0000", "Brufen", "Galenika", true, 249, false);
		Lek lek2 = new Lek("N0001", "Kafetin", "Alkaloid", false, 169, false);
		Lek lek3 = new Lek("N0002", "Lorazepam", "Hemofarm", true, 409, false);
		zapisiLekovi.add(lek1);		
		zapisiLekovi.add(lek2);
		zapisiLekovi.add(lek3);	*/		// obnavlja bazu
		
		
		File f = new File("Lekovi.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		try {
			oos.writeObject(Lekovi);
		} finally {
			oos.close();
		}
	}
	
	public static void writeToFileRec(Recept noviRec) throws IOException, ClassNotFoundException {
		ArrayList<Recept> ucitaniRecepti = readFromFile.readFromFileRec();
		ucitaniRecepti.add(noviRec);
		  
		File f = new File("Recepti.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		try {
			oos.writeObject(ucitaniRecepti);
		} finally {
			oos.close();
		}
	}
	
	public static void updateDatabaseRec(ArrayList<Recept> Recepti) throws ClassNotFoundException, IOException {
		/*ArrayList<Recept> zapisiRecepti = new ArrayList<Recept>();
		ArrayList<Korisnik> ucitanikor = readFromFile.readFromFileKor();
		ArrayList<Lek> ucitanilek = readFromFile.readFromFileLek();
		Date date = new Date(System.currentTimeMillis());
		
		Recept rec1 = new Recept(1, ucitanikor.get(0).getKorisnickoIme(), "0501668836978", date);
		rec1.dodajLek(ucitanilek.get(0), 1);
		rec1.dodajLek(ucitanilek.get(6), 2);
		Recept rec2 = new Recept(2, ucitanikor.get(1).getKorisnickoIme(), "3018668836978", date);
		rec2.dodajLek(ucitanilek.get(0), 3);
		Recept rec3 = new Recept(3, ucitanikor.get(2).getKorisnickoIme(), "1605668836978", date);
		rec3.dodajLek(ucitanilek.get(2), 2);
		zapisiRecepti.add(rec1);		
		zapisiRecepti.add(rec2);
		zapisiRecepti.add(rec3);		*/	// obnavlja bazu
		
		
		File f = new File("Recepti.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		try {
			oos.writeObject(Recepti);
		} finally {
			oos.close();
		}
	}
	
	public static void writeToFileProdaja(Prodaja prodaja) throws IOException, ClassNotFoundException {
		File f = new File("Prodaja.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		try {
			oos.writeObject(prodaja);
		} finally {
			oos.close();
		}
	}
	
	public static void writeToFileProdajaAddRacun(Racun racun) throws IOException, ClassNotFoundException {
		Prodaja prodaja = readFromFile.readFromFileProdaja();
		prodaja.getKupovine().add(racun);
		
		File f = new File("Prodaja.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		try {
			oos.writeObject(prodaja);
		} finally {
			oos.close();
		}
	}
}

