package com.generation.fileuploadblobdatabase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.generation.fileuploadblobdatabase.dto.MessaggioDto;


/**
 * classe per gestire eccezioni

 */
@ControllerAdvice
public class FileUploadExceptionAdvice {

	/**
	 * Gestisce le eccezioni quando l'utente cerca di caricare un file di dimensioni superiori <br> 
	 * a quelle fissate in application.properties
	 * @param exc : eccezione file troppo grande
	 * @return messaggio
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<MessaggioDto> gestoreMaxSizeException(MaxUploadSizeExceededException exc){
		
		// application.properties:
		// spring.servlet.multipart.max-file-size=2MB
		// spring.servlet.multipart.max-request-size=2MB
		
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
				.body(new MessaggioDto("File troppo Grande!"));
		
	}
}
