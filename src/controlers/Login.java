package controlers;

import java.io.IOException;
import java.util.ArrayList;

import model.Korisnik;

public class Login {

	public static boolean authenticate(String username, String password) throws ClassNotFoundException, IOException {
		ArrayList<Korisnik> kor = readFromFile.readFromFileKor();
		for (int i = 0; i < kor.size(); i++) {
	        // hardcoded username and password
	        if (username.equals(kor.get(i).getKorisnickoIme()) && password.equals(kor.get(i).getLozinka())) {
	            return true;
	        }
		}
        return false;
    }
	
	
}
