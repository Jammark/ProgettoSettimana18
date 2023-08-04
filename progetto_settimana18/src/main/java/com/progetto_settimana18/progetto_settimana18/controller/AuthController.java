package com.progetto_settimana18.progetto_settimana18.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.progetto_settimana18.progetto_settimana18.common.JWTTools;
import com.progetto_settimana18.progetto_settimana18.common.UnauthorizedException;
import com.progetto_settimana18.progetto_settimana18.model.User;
import com.progetto_settimana18.progetto_settimana18.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UserService usersService;

	@Autowired
	JWTTools jwtTools;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody UserRegisterPayload body) {
		User created = usersService.save(body);

		return created;
	}

	@PostMapping("/login")
	public LoginSuccessfullPayload login(@RequestBody UserLoginPayload body) {
		// 1. Verifichiamo che l'email dell'utente sia presente nel db

		User user = usersService.findByUsername(body.getUsername());

		// 2. In caso affermativo, devo verificare che la pw corrisponda a quella
		// trovata nel db
		if (body.getPassword().equals(user.getPassword())) {

			// 3. Se le credenziali sono OK --> genero un JWT e lo invio come risposta
			String token = jwtTools.createToken(user);
			return new LoginSuccessfullPayload(token);

		} else {
			// 4. Se le credenziali NON sono OK --> 401
			throw new UnauthorizedException("Credenziali non valide!");
		}
	}

}
