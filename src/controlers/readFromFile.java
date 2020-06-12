package controlers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import model.Korisnik;
import model.TipKorisnika;

public class readFromFile {
	
	public static ArrayList<Korisnik> readFromFileKor() throws IOException, ClassNotFoundException {
		
		File f = new File("Korisnici.txt");
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
		
		try {
			ArrayList<Korisnik> ucitaniKorisnici = new ArrayList<Korisnik>(); 
			ucitaniKorisnici = (ArrayList<Korisnik>)ois.readObject();
			
			/*System.out.println(ucitaniKorisnici.get(ucitaniKorisnici.size()-1));/*
			System.out.println(ucitaniKorisnici.get(1));
			System.out.println(ucitaniKorisnici.get(2));
			System.out.println(ucitaniKorisnici.get(2).getLozinka());
			int br = ucitaniKorisnici.size();
			System.out.println(br);
			for (int i = 0; i < br; i++) {
				if (ucitaniKorisnici.get(i).getTipKorisnika() == TipKorisnika.LEKAR) {
					System.out.println(ucitaniKorisnici.get(i).getIme());
				}
			}*/
			return ucitaniKorisnici;
		} finally {
			ois.close();
		}
	}
}
/*
public static void readFromFile() throws IOException {
	Korisnik kor1 = new Korisnik("Admin", "admin", "Administrator", "Adminović", TipKorisnika.ADMINISTRATOR);
	Korisnik kor2 = new Korisnik("Ana", "ankica", "Ana", "Peric", TipKorisnika.LEKAR);
	Korisnik[] korisnici = new Korisnik[] { kor1, kor2};
	List<Korisnik>korisnici1 = new ArrayList<Korisnik>();
	korisnici1.add(kor1);
	korisnici1.add(kor2);

	File f = new File("Korisnici.xml");
	
	OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
	  
	try {

		XStream xs = new XStream();
		xs.alias("korisnik", Korisnik.class);

		String s = xs.toXML(korisnici1); 
		xs.toXML(korisnici1, os); //zapisuje u fajl
		System.out.println("OVO" + s);

		Korisnik[] ucitaniKorisnici = (Korisnik[]) xs.fromXML(s);
		int br = ucitaniKorisnici.length;
		for (int i = 0; i < br; i++) {
			if (ucitaniKorisnici[i].getTipKorisnika() == TipKorisnika.ADMINISTRATOR) {
				System.out.println(ucitaniKorisnici[i].getIme());
				
			}
		}
	} finally {
		os.close();
	}
}
*/
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