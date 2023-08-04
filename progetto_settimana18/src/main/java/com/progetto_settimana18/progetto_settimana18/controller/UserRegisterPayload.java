package com.progetto_settimana18.progetto_settimana18.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterPayload extends UserRequestPayload {

	private String password;

}
