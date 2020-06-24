package controlers;

import java.io.IOException;
import java.util.ArrayList;

import model.Korisnik;

public class Login {

	public static boolean authenticate(String username, String password) throws ClassNotFoundException, IOException {
		ArrayList<Korisnik> kor = readFromFile.readFromFileKor();
		for (int i = 0; i < kor.size(); i++) {
	        if (username.equals(kor.get(i).getKorisnickoIme()) && password.equals(kor.get(i).getLozinka()) && kor.get(i).getLogickiObrisan() ==  false) {
	            return true;
	        }
		}
        return false;
    }
}
