package com.progetto_settimana18.progetto_settimana18.common;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorPayload {

	private String message;
	private LocalDate timestamp;

}
