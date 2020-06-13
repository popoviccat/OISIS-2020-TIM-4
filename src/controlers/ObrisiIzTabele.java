package controlers;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import model.Korisnik;
import view.CreateTableKorisnik;
import java.lang.String;

public class ObrisiIzTabele {
	
	public static void ObrisiKor (int ct, String name) throws ClassNotFoundException, IOException{
		
		ArrayList<Korisnik> korisnici = readFromFile.readFromFileKor();
		 if (ct != -1 ) {
	        int size = korisnici.size();
	        for (int i = 0; i < size; i++) {
	        	if (name.equals((String)korisnici.get(i).getKorisnickoIme())) { //trazi isto ime
	        		
	        		JFrame frame= new JFrame();
	        		int code=JOptionPane.showConfirmDialog(frame, "Da li ste sigurni da zelite da obrisete korisnika " + "\n" +
	        				korisnici.get(i).getIme() + " " +
	        				korisnici.get(i).getPrezime() + "?",
	        				"Obrisi korisnika?",JOptionPane.YES_NO_OPTION);
	        				 
	        		if (code == JOptionPane.YES_OPTION){ //brisanje
	        				
	        			korisnici.get(i).setLogickiObrisan(true);
	        			writeToFile.updateDatabaseKor(korisnici);
	        			int br = korisnici.size();
	        			System.out.println("OBRISAN" + korisnici.get(i).getIme() + " " + korisnici.get(i).getPrezime());
	        				
	        			/*for (int j = 0; j < br; j++) {	// OBRISANI
	        				if (korisnici.get(j).getLogickiObrisan() == true) {
	        					System.out.println(korisnici.get(j).getKorisnickoIme() + korisnici.get(j).getPrezime());
	        				}
	        			}*/
	        			System.out.println("BRISANJE");
		        		JOptionPane.showMessageDialog(null, "Korisnik je obrisan.");
		        		break;
		        				
	        		} else {
	        			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        		}
	        	}
	        }
		 }
	}
}
