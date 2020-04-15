package minpakke;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ansatt", schema = "oblig3")
public class Ansatt {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OneToMany(mappedBy = "ansatt")
	private int ansattId;
	@Column(unique=true)
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate dato;
	private String stilling;
	private Integer maanedslonn;
	
	@OneToOne
	@JoinColumn(name = "avdelingId", referencedColumnName = "avdelingId")
    private Avdeling avdeling;
	
	@OneToMany
	(mappedBy="ansatt",fetch = FetchType.EAGER)
	private List<Prosjekt_Ansatte> prosjekt;

	public Ansatt() {

	}
	public Ansatt(String brukernavn, String navn, String etternavn, 
			LocalDate dato, String stilling, Integer lonn, Avdeling avdeling) {
		this.brukernavn = brukernavn;
		this.fornavn = navn;
		this.etternavn = etternavn;
		this.dato = dato;
		this.stilling = stilling;
		maanedslonn = lonn;
		this.avdeling = avdeling;

		

	}
	public void addProsjekt(Prosjekt_Ansatte k) {
		prosjekt.add(k);
	}
	
	public Avdeling getAvdeling() {
		return avdeling;
	}
	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}
	public int getAnsattID() {
		return ansattId;
	}
	
	public String getBrukernavn() {
		return brukernavn;
	}
	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}
	public String getNavn() {
		return fornavn;
	}
	public void setNavn(String navn) {
		this.fornavn = navn;
	}
	public String getEtternavn() {
		return etternavn;
	}
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	public LocalDate getDato() {
		return dato;
	}
	public void setDato(LocalDate dato) {
		this.dato = dato;
	}
	public String getStilling() {
		return stilling;
	}
	public void setStilling(String stilling) {
		this.stilling = stilling;
	}
	public Integer getMaanadslonn() {
		return maanedslonn;
	}
	public void setMaanadslonn(Integer maanadslonn) {
		this.maanedslonn = maanadslonn;
	}
	public List<Prosjekt_Ansatte> getProsjekt() {
		return prosjekt;
	}
	public void setProsjekt(List<Prosjekt_Ansatte> prosjekt) {
		this.prosjekt = prosjekt;
	}
	@Override
	public String toString() {
		return "Ansatt [ansattId=" + ansattId + ", brukernavn=" + brukernavn + ", fornavn=" + fornavn + ", etternavn="
				+ etternavn + ", dato=" + dato + ", stilling=" + stilling + ", maanedslonn=" + maanedslonn
				+ ", avdeling=" + avdeling.toString() + ", prosjekt=" + prosjekt.toString() + "]";
	}

	
	
	
}
