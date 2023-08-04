package com.progetto_settimana18.progetto_settimana18.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.progetto_settimana18.progetto_settimana18.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	JWTTools jwttools;
	@Autowired
	UserService usersService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		// authHeader --> Bearer
		// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMjc3MzUwNS1kOWUxLTRiMmMtYjJkZS1iNWJlZGVlZTg5MzYiLCJpYXQiOjE2OTEwNTg5MzcsImV4cCI6MTY5MTY2MzczN30.n-v6f5vdE8l8Nv3sa73H2P4DvZFV-BCWm40B2mnB8cE

		if (authHeader == null || !authHeader.startsWith("Bearer "))
			throw new UnauthorizedException("Per favore passa il token nell'authorization header");
		String token = authHeader.substring(7);
		System.out.println("TOKEN -------> " + token);

		// 2. Verifico che il token non sia nè scaduto nè sia stato manipolato
		jwttools.verifyToken(token);

		// 3. Se è tutto OK

		// 3.3 Puoi procedere al prossimo blocco della filter chain
		filterChain.doFilter(request, response);

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

		// return new AntPathMatcher().match("/auth/**", request.getServletPath());
		return true;
	}

}
