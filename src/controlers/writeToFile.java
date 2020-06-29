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
		Korisnik kor1 = new Korisnik("admin", "admin", "Administrator", "Adminovic", TipKorisnika.ADMINISTRATOR, false);
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

