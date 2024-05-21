package com.generation.fileupload.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.generation.fileupload.config.CustomProperties;



@Entity
public class Veicolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;	
	private String fileName;	
	
	// metodo che gera l'url
    public String getUrl() {

		// 1) percorso immagine di default se non è stata caricata
		// 2) percorso immagine caricata

		if (fileName == null || fileName.equals("")) {
        	//1
        	return "/" + CustomProperties.DEFAULT_IMG_PATH;        	
        }

        //2
        return "/" + CustomProperties.IMG_URL_PATH + "/" + id + "/" + fileName;
    }
	
	// getters e setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}






	

	
	
}
