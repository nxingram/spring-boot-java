package com.generation.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Esempio =>
 * https://github.com/habuma/spring-in-action-5-samples/tree/master/ch04/tacos/src/main/java/tacos/security
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService _UserDetailsSrv; // implementato da UserService

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		// esclusioni: non vengono parsate da HttpSecurity, ha la precedenza
//
//		web
//		 .ignoring()
//		 	.antMatchers("/rottavisibile/**");
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests() // richieste autorizzate			
				.antMatchers("/user") // 1 o + rotte User
					.hasAnyRole("USER","ADMIN") // ruolo dell'utente che può accedere (concatena 'ROLE_' con la stringa passata in argomento)
					
				.antMatchers("/admin") // 1 o + rotte Admin
					.hasRole("ADMIN") // ruolo dell'utente che può accedere	(concatena 'ROLE_' con la stringa passata in argomento)
					
				.antMatchers("/**") // 1 o + rotte non loggati
					.permitAll() // possono accedere tutti, senza login
					    
			.and()
				.formLogin()
					.loginPage("/login") // rotta che esegue controllo username e password automatico di spring security
					.defaultSuccessUrl("/") // login corretto, vado sulla index
					.failureUrl("/login?error=true") // login sbagliato, vado a pagina errori
			.and()
				.logout()
					.logoutUrl("/logout") // <= valore già di default (non necessario)
					.logoutSuccessUrl("/") // redireziona a questa rotta dopo il logout
			      		
			.and()
				.httpBasic()
			.and()
				.csrf()
			    	.disable();
			  

	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		// 1) Cripta le password che gli vengono passate
		// serve anche per confrontare la password dell'utente con quella salvata su database (criptata) e autenticarlo
		
		//1
		return new BCryptPasswordEncoder();
		
		
		
		// Spring security 5
		// https://blog.marcosbarbero.com/password-encoder-migration-spring-security-5/
		// supporto per password criptate con vari password encoders
		
		//return PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 1) imposto il servizio che implementa l'interfaccia UserDetailsService, 
		// in questo esempio coincide con il nostro UserService, che la implementa
		// 2) imposto il password encoder
		
		auth
			.userDetailsService(_UserDetailsSrv) //1
			.passwordEncoder(this.passwordEncoder()); //2
	}

}
