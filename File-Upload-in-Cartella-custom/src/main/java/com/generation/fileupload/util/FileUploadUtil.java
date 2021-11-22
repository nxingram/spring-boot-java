package com.generation.fileupload.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		
		// 1) converte percorso stringa in un path
		// 2) crea cartella se non esiste dove salvare l'immagine
		// 3) sovrascrive file se già presente con stesso nome
		
		//1
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) { 
			//2
			Files.createDirectories(uploadPath); // throws IOException
		}
		try(InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName); // percorso file completo
			//3 
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
		
		
		
	}
}
