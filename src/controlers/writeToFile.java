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
import model.TipKorisnika;

public class writeToFile {
	
	public static void writeToFile(Korisnik noviKorisnik) throws IOException, ClassNotFoundException {
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
			oos.close(); //Zatvara i tok nizeg nivoa.
		}
		
	}
}
