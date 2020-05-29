package controlers;

import model.Korisnik;

public class Login {

	public static boolean authenticate(String username, String password) {
		Korisnik korisnik = new Korisnik();
        // hardcoded username and password
        if (username.equals("admin") && password.equals("admin")) {
            return true;
        }
        return false;
    }
	
}
