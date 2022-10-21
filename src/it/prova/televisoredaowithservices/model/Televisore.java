package it.prova.televisoredaowithservices.model;

import java.util.Date;

public class Televisore {
	private Long id;
	private String marca;
	private String modello;
	private int pollici;
	private Date dataProduzione;

	public Televisore() {
	}

	public Televisore(String marca, String modello) {
		this.marca = marca;
		this.modello = modello;
	}

	public Televisore(String marca, String modello, int pollici, Date dateProduzione) {
		this.marca = marca;
		this.modello = modello;
		this.pollici = pollici;
		this.dataProduzione = dateProduzione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public int getPollici() {
		return pollici;
	}

	public void setPollici(int pollici) {
		this.pollici = pollici;
	}

	public Date getDataProduzione() {
		return dataProduzione;
	}

	public void setDataProduzione(Date dataProduzione) {
		this.dataProduzione = dataProduzione;
	}

	public String toString() {
		return "televisore: [marca: " + this.marca + ", modello: " + this.modello + ", pollici: " + this.pollici
				+ ", dataproduzione: " + this.dataProduzione + "]";
	}
}
