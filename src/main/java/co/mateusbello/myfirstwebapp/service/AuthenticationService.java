package co.mateusbello.myfirstwebapp.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	public boolean authenticate(String username, String password) {
		if (username.equalsIgnoreCase("test") &&
				password.equals("1234")) {
			return true;
		}
		return false;
	}
}
