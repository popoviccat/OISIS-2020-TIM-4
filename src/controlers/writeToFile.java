package controlers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import model.Korisnik;
import model.Lek;
import model.TipKorisnika;

public class writeToFile {
	
	public static void writeToFileKor(Korisnik noviKorisnik) throws IOException, ClassNotFoundException {
		ArrayList<Korisnik> ucitaniKorisnici = readFromFile.readFromFileKor();
		/*Korisnik kor1 = new Korisnik("admin", "admin", "Administrator", "Adminović", TipKorisnika.ADMINISTRATOR);
		Korisnik kor2 = new Korisnik("ankic", "ankica", "Ana", "Peric", TipKorisnika.LEKAR);
		Korisnik kor3 = new Korisnik("laki", "laki", "Laza", "Lazic", TipKorisnika.APOTEKAR);
		//ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>(); 
		ucitaniKorisnici.add(kor1);
		ucitaniKorisnici.add(kor2);
		ucitaniKorisnici.add(kor3);*/
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
}
