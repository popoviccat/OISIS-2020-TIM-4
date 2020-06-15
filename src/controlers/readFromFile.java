package controlers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import model.Korisnik;
import model.Lek;
import model.Recept;
import model.TipKorisnika;

public class readFromFile {
	
	public static ArrayList<Korisnik> readFromFileKor() throws IOException, ClassNotFoundException {
		
		File f = new File("Korisnici.txt");
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
		
		try {
			ArrayList<Korisnik> ucitaniKorisnici = new ArrayList<Korisnik>(); 
			ucitaniKorisnici = (ArrayList<Korisnik>)ois.readObject();
	
			return ucitaniKorisnici;
		} finally {
			ois.close();
		}
	}
	
	public static ArrayList<Lek> readFromFileLek() throws IOException, ClassNotFoundException {
		
		File f = new File("Lekovi.txt");
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
		
		try {
			ArrayList<Lek> ucitaniLekovi = new ArrayList<Lek>(); 
			ucitaniLekovi = (ArrayList<Lek>)ois.readObject();
	
			/*System.out.println(ucitaniLekovi.get(ucitaniLekovi.size()-1));/*
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
				
			return ucitaniLekovi;
		} finally {
			ois.close();
		}
	}
	
	public static ArrayList<Recept> readFromFileRec() throws IOException, ClassNotFoundException {
		
		File f = new File("Recepti.txt");
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
		
		try {
			ArrayList<Recept> ucitaniRecepti = new ArrayList<Recept>(); 
			ucitaniRecepti = (ArrayList<Recept>)ois.readObject();
	
			return ucitaniRecepti;
		} finally {
			ois.close();
		}
	}
}