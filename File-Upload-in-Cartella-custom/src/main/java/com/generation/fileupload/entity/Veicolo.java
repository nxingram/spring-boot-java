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
	private String photo;

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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	@Transient // non viene salvata su database
    public String getPhotosImagePath() {

		// 1) percorso immagine di default se non è stata caricata
		// 2) percorso immagine caricata

		if (photo == null || photo.equals("")) {
        	//1
        	return "/" + CustomProperties.defaultImg;        	
        }

        //2
        return "/" + CustomProperties.basepath + "/" + id + "/" + photo;
    }

	
	
}
