package com.generation.gestionale.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

/**
 * Usa Validation Api da usare insieme a @Valid nel controller
 */
@Entity
@Table(name = "ordine_dettaglio")
public class OrdineDettaglio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * name: nome di questo campo sulla tabella <br>
	 * nullable: se la foreign key può essere nulla
	 */
	@ManyToOne
	@JoinColumn(name = "ordine_id", nullable = false)
	private Ordine ordine;

	/**
	 * name: nome di questo campo sulla tabella <br>
	 * nullable: se la foreign key può essere nulla
	 */
	@ManyToOne
	@JoinColumn(name = "articolo_id", nullable = false)
	private Articolo articolo;

	/**
	 * validation
	 */
	@Positive
	@Max(value = 255)
	private Short quantita;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public Short getQuantita() {
		return quantita;
	}

	public void setQuantita(Short quantita) {
		this.quantita = quantita;
	}	
	

}
