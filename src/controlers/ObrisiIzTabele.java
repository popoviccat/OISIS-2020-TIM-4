package controlers;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import model.Korisnik;
import model.Lek;
import java.lang.String;

public class ObrisiIzTabele {
	
	public static void ObrisiKor (int ct, String name) throws ClassNotFoundException, IOException{
		
		ArrayList<Korisnik> korisnici = readFromFile.readFromFileKor();
		 if (ct != -1 ) {
	        int size = korisnici.size();
	        for (int i = 0; i < size; i++) {
	        	if (name.equals((String)korisnici.get(i).getKorisnickoIme())) {
	        		
	        		JFrame frame= new JFrame();
	        		int code=JOptionPane.showConfirmDialog(frame, "Da li ste sigurni da zelite da obrisete korisnika " + "\n" +
	        				korisnici.get(i).getIme() + " " +
	        				korisnici.get(i).getPrezime() + "?",
	        				"Obrisi korisnika?",JOptionPane.YES_NO_OPTION);
	        				 
	        		if (code == JOptionPane.YES_OPTION){
	        				
	        			korisnici.get(i).setLogickiObrisan(true);
	        			writeToFile.updateDatabaseKor(korisnici);
	        			int br = korisnici.size();
	        			
		        		JOptionPane.showMessageDialog(null, "Korisnik je obrisan.");
		        		break;
		        				
	        		} else {
	        			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        		}
	        	}
	        }
		 }
	}
	
	public static void ObrisiLek (int ct, String name) throws ClassNotFoundException, IOException{
		
		ArrayList<Lek> lekovi = readFromFile.readFromFileLek();
		 if (ct != -1 ) {
	        int size = lekovi.size();
	        for (int i = 0; i < size; i++) {
	        	if (name.equals((String)lekovi.get(i).getSifra())) {
	        		
	        		JFrame frame= new JFrame();
	        		int code=JOptionPane.showConfirmDialog(frame, "Da li ste sigurni da zelite da obrisete lek " + "\n" +
	        				lekovi.get(i).getIme() +
	        				", proizvodjaca " +
	        				lekovi.get(i).getProizvodjac() + "?",
	        				"Obrisi lek?",JOptionPane.YES_NO_OPTION);
	        				 
	        		if (code == JOptionPane.YES_OPTION){
	        				
	        			lekovi.get(i).setLogickiObrisan(true);
	        			writeToFile.updateDatabaseLek(lekovi);
	        			int br = lekovi.size();
	        			
		        		JOptionPane.showMessageDialog(null, "Lek je obrisan.");
		        		break;
		        				
	        		} else {
	        			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        		}
	        	}
	        }
		 }
	}
}
