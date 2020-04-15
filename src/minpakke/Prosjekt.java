package minpakke;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prosjekt", schema = "oblig3")
public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektId;
	private String navn;
	private String beskrivelse;
	
	
	public Prosjekt() {
		
	}
	@Override
	public String toString() {
		return "Prosjekt [prosjektId=" + prosjektId + ", navn=" + navn + ", beskrivelse=" + beskrivelse +"]";
	}
	public Prosjekt(String navn, String beskrivelse) {
		this.navn = navn;
		this.beskrivelse = beskrivelse;
	}
	public Integer getProsjektId() {
		return prosjektId;
	}
	public void setProsjektId(Integer prosjektId) {
		this.prosjektId = prosjektId;
	}
	public String getNavn() {
		return navn;
	}
	public void setNavn(String navn) {
		this.navn = navn;
	}
	public String getBeskrivelse() {
		return beskrivelse;
	}
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	
	
}
