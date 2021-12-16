package com.generation.fileuploadblobdatabase.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "veicoloblob")
public class Veicolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/**
	 * nome del veicolo
	 */
	private String name;
	
	/**
	 * nome del file
	 */
	private String fileName;
	
	/**
	 * mime type del file
	 */
	private String type;
	
	/**
	 * @Lob : array di bytes, mappato in un BLOB
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] dataBlob;
 
	public Veicolo() {
	}

	public Veicolo(String name, String fileName, String type, byte[] data) {
		this.name = name;
		this.fileName = fileName;
		this.type = type;
		this.dataBlob = data;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return dataBlob;
	}

	public void setData(byte[] data) {
		this.dataBlob = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
