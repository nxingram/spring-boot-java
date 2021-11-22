package com.generation.gestionale.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.generation.gestionale.entity.enumtype.CategoriaEnum;

/**
 * Usa Validation Api da usare insieme a @Valid nel controller
 */
@Entity
public class Articolo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * validation
	 */
	@Size(max = 100, message = "troppo grande")
	private String descrizione;

	@DecimalMax(value = "9999.99")
	private BigDecimal prezzo;

	/**
	 * caso speciale per gli enum stringa
	 */
	@Column(columnDefinition = "ENUM('hardware', 'software')")
	@Enumerated(EnumType.STRING)
	private CategoriaEnum categoria;

	@Positive
	@Max(value = 255)
	private Integer rimanenza;

	/**
	 * necessario per la relazione molti a molti e terza tabella relazionale <br>
	 * mappedBy: nome della proprietà (foreign key) della classe corrispondente (OrdineDettaglio.articolo)<br>
	 * JsonIgnore: non converte in json questa proprietà, che altrimenti va in loop
	 * infinito
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "articolo")
	private List<OrdineDettaglio> ordiniDettaglio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public CategoriaEnum getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEnum categoria) {
		this.categoria = categoria;
	}

	public Integer getRimanenza() {
		return rimanenza;
	}

	public void setRimanenza(Integer rimanenza) {
		this.rimanenza = rimanenza;
	}

	public List<OrdineDettaglio> getOrdiniDettaglio() {
		return ordiniDettaglio;
	}

	public void setOrdiniDettaglio(List<OrdineDettaglio> ordiniDettaglio) {
		this.ordiniDettaglio = ordiniDettaglio;
	}



}