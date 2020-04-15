package minpakke;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prosjekt_ansatte", schema = "oblig3")
public class Prosjekt_Ansatte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektDeltagelseId;
	
	@ManyToOne
	@JoinColumn(name = "ansattId", referencedColumnName="ansattId")
	private Ansatt ansatt;
	
	@ManyToOne
	@JoinColumn(name = "prosjektId", referencedColumnName = "prosjektId")
	private Prosjekt prosjekt;
	
	
	private String rolle;
	private Integer arbeidstimer;
	
	@Column(name = "er_aktiv", columnDefinition = "BIT", nullable = false)
	private char er_aktiv;
	
	public Prosjekt_Ansatte() {
}
	public Prosjekt_Ansatte(Ansatt ansatt, Prosjekt prosjekt, String rolle,
			Integer arbeidstimer, char er_Aktiv) {
		this.ansatt = ansatt;
		this.prosjekt = prosjekt;
		this.rolle = rolle;
		this.arbeidstimer = arbeidstimer;
		this.er_aktiv = er_Aktiv;
		
}
	@Override
	public String toString() {
		return "Prosjekt_Ansatte [ansatt=" + ansatt.getBrukernavn() + ", prosjekt=" + prosjekt.getNavn() + ", rolle=" + rolle + ", arbeidstimer="
				+ arbeidstimer + ", er_aktiv=" + er_aktiv + "]";
	}
	public Ansatt getAnsatt() {
		return ansatt;
	}
	
	public void leggTilTimer(int timer){
		this.arbeidstimer = this.arbeidstimer + timer;
	}
	
	public void setAnsattId(Ansatt ansatt) {
		this.ansatt = ansatt;
	}
	public Prosjekt getProsjekt() {
		return prosjekt;
	}
	public void setProsjekt(Prosjekt prosjekt) {
		this.prosjekt = prosjekt;
	}
	public String getRolle() {
		return rolle;
	}
	public void setRolle(String rolle) {
		this.rolle = rolle;
	}
	public Integer getArbeidstimer() {
		return arbeidstimer;
	}
	public void setArbeidstimer(Integer arbeidstimer) {
		this.arbeidstimer = arbeidstimer;
	}
	public char isEr_aktiv() {
		return er_aktiv;
	}
	public void setEr_aktiv(char er_aktiv) {
		this.er_aktiv = er_aktiv;
	}
}

