package controlers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;

import model.Korisnik;
import model.TipKorisnika;

public class readFromFile {
	
	public static void readFromFileKor() throws IOException {
		
		String korisnici = new String();
		File f = new File("Korisnici.xml");
		
		
		  
		try {

			XStream xs = new XStream();
			

			Korisnik[] ucitaniKorisnici = (Korisnik[]) xs.fromXML(korisnici);
			System.out.println("BREAK");
			int br = ucitaniKorisnici.length;
			for (int i = 0; i < br; i++) {
				if (ucitaniKorisnici[i].getTipKorisnika() == TipKorisnika.ADMINISTRATOR) {
					System.out.println(ucitaniKorisnici[i].getIme());
					
				}
			}
		} finally {
			
		}
	}
}
/*
InputStream is = new BufferedInputStream(new FileInputStream(f));
XStream xs = new XStream();
xs.alias("korisnik", Korisnik.class);

String s = xs.toXML(korisnici); 
xs.toXML(korisnici, os); 
System.out.println(s);

Korisnik kor3 = new Korisnik();
Korisnik kor4 = new Korisnik();
Korisnik[] ucitaniKorisnici = new Korisnik[] {kor3,kor4};// = (Korisnik[]) xs.fromXML(s);

Korisnik cor = (Korisnik)xs.fromXML(is);
System.out.println(cor.getIme());
*/