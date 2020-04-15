package minpakke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class AnsattMain {

	public static void main(String[] args) {

//		meny();
		
		System.out.println("GRUPPE NR 6");
		System.out.println("MATIAS VEDELER CERDA, STUD NR : 582618");
		System.out.println("SIMON KOBBERNES, STUD NR : 584958");
		System.out.println("CHRISTIAN EVENSEN, STUD NR : 584988");

	}

	public static void meny() {
		AnsattDA0 ansattDao = new AnsattDA0();
		AvdelingDAO avdelingDao = new AvdelingDAO();
		Prosjekt_AnsatteDAO Prosjekt_ADAO = new Prosjekt_AnsatteDAO();
		ProsjektDAO ProsjektDao = new ProsjektDAO();
		Scanner sc = new Scanner(System.in);
		String input, input1, bNavn, navn, eNavn, stilling, sjef,rolle = null;
		Integer lonn, avdelingT,ansattId, arbeidstimer = null;
		char er_aktiv;
		LocalDate dato = null;
		boolean fortsett = true;
		Ansatt ansatt = null;
		Avdeling avdeling = null;
		Prosjekt prosjekt = null;
		Prosjekt_Ansatte prosjekt_A = null;

		System.out.println("------------------------------");

		while (fortsett) {
			System.out.println("Velkommen til Ansatt sin Kommandolinje");
			System.out.println("S = Søke etter ansatt med ansatt-id");
			System.out.println("A = Søke etter avdeling med avdeling-id");
			System.out.println("B = Søke etter ansatt på brukernavn");
			System.out.println("P = Utlisting av alle ansatte");
			System.out.println("T = Utlisting av alle ansatte på en avdeling");
			System.out.println("F = Utlisting av alle ansatte på et prosjekt");
			System.out.println("K = Oppdatere en ansatt sin stilling");
			System.out.println("L = Oppdatere en ansatt sin lønn");
			System.out.println("C = Registrere timer for ansatte på prosjekt");
			System.out.println("E = Oppdatere en ansatt sin avdeling");
			System.out.println("N = Legge til en ny Ansatt");
			System.out.println("W = Legge til en ny Avdeling");
			System.out.println("O = legge til en ny prosjekt");
			System.out.println("R = Registrere prosjektdeltagelse");
			System.out.println("Q = Avslutt");
			input = sc.nextLine();

			switch (input) {
			case "S":
				System.out.println("Ansattid");
				input = sc.nextLine();
				ansatt = ansattDao.finnAnsattMedId(Integer.parseInt(input));
				System.out.println(ansatt.toString());
				break;
			case "B":
				System.out.println("Brukernavn");
				input = sc.nextLine();
				ansatt = ansattDao.finnAnsattMedBrukernavn(input);
				System.out.println(ansatt.toString());
				break;
			case "P":
				ansattDao.skrivUtAlle();
				break;
			case "T":
				System.out.println("Tast inn avdelingsid");
				input = sc.nextLine();
				avdelingDao.skrivUtAlle(Integer.parseInt(input));
				break;
			case "K":
				System.out.println("Skriv ansattId");
				input = sc.nextLine();
				System.out.println("Skriv Stilling");
				input1 = sc.nextLine();
				ansattDao.oppdaterStilling(input1, Integer.parseInt(input));
				break;
			case "L":
				System.out.println("Skriv ansattId");
				input = sc.nextLine();
				System.out.println("Skriv Lonn");
				input1 = sc.nextLine();
				ansattDao.oppdaterLonn(Integer.parseInt(input1), Integer.parseInt(input));
				break;
			case "C":
				System.out.println("Skriv ansattId");
				input = sc.nextLine();
				System.out.println("Skriv Prosjektid");
				input1 = sc.nextLine();
				System.out.println("Skriv Timer");
				lonn = Integer.parseInt(sc.nextLine());
				prosjekt_A = Prosjekt_ADAO.finnProsjekt_A(Integer.parseInt(input), Integer.parseInt(input1));
				if(prosjekt_A != null) {
				Prosjekt_ADAO.registrerTimer(lonn, prosjekt_A);
				}
				System.out.println(lonn + " timer registrert på " + prosjekt_A.getAnsatt().getNavn() + " på  prosjekt " + prosjekt_A.getProsjekt().getNavn());
				break;
			case "E":
				System.out.println("Skriv ansattId");
				input = sc.nextLine();
				System.out.println("Skriv Avdelingid");
				input1 = sc.nextLine();
				avdeling = avdelingDao.finnAvdelingMedId(Integer.parseInt(input1));
				if (avdeling != null) {
					ansattDao.oppdaterAvdeling(avdeling, Integer.parseInt(input));
					System.out.println();
				} else {
					System.out.println("Fant ikke avdeling");
				}
				break;
			case "N":
				System.out.println("Tast inn Brukernavn");
				bNavn = sc.nextLine();

				System.out.println("Navn");
				navn = sc.nextLine();

				System.out.println("Etternavn");
				eNavn = sc.nextLine();

				System.out.println("Dato");
				dato = LocalDate.parse(sc.nextLine());

				System.out.println("Stilling");
				stilling = sc.nextLine();

				System.out.println("Lonn");
				lonn = Integer.parseInt(sc.nextLine());

				System.out.println("Avdeling");
				avdelingT = Integer.parseInt(sc.nextLine());
				avdeling = avdelingDao.finnAvdelingMedId(avdelingT);
				Ansatt f = new Ansatt(bNavn, navn, eNavn, dato, stilling, lonn, avdeling);
				ansattDao.leggTil(f);
				System.out.println(bNavn + " lagt til med ansattId " + f.getAnsattID());
				break;

			case "W":
				System.out.println("Navn");
				navn = sc.nextLine();

				System.out.println("Velg brukernavn til en ansatt som blir sjef til avdelingen");
				sjef = sc.nextLine();

				ansatt = ansattDao.finnAnsattMedBrukernavn(sjef);

				if (ansatt.getAvdeling().getSjef() != ansatt) {
					avdeling = new Avdeling(navn, ansatt);
					avdelingDao.leggTil(avdeling, ansatt);
				} else {
					System.out.println("Ansatt er allerede sjef i en annen avdeling!");
				}
				break;

			case "A":
				System.out.println("Avdeling.id");
				input = sc.nextLine();
				avdeling = avdelingDao.finnAvdelingMedId(Integer.parseInt(input));
				System.out.println(avdeling.toString());
				break;

			case "O":
				System.out.println("Navn");
				input = sc.nextLine();
				System.out.println("Beskrivelse");
				input1 = sc.nextLine();
				prosjekt = new Prosjekt(input, input1);
				System.out.println("prosjekt " + input + "lagt til");
				break;
			case "R":
				System.out.println("ansattId");
				ansattId = (Integer.parseInt(sc.nextLine()));
				ansatt = ansattDao.finnAnsattMedId(ansattId);

				System.out.println("ProsjektId");
				prosjekt = ProsjektDao.finnProsjekt(Integer.parseInt(sc.nextLine()));

		

				System.out.println("Rolle");
				rolle = sc.nextLine();

				System.out.println("Arbeidstimer");
				arbeidstimer = Integer.parseInt(sc.nextLine());

				System.out.println("Aktiv? velg T eller F");
				er_aktiv = sc.nextLine().charAt(0);

				prosjekt_A = new Prosjekt_Ansatte(ansatt, prosjekt, rolle, arbeidstimer, er_aktiv);

				Prosjekt_ADAO.leggTilAnsattTilProsjekt(prosjekt.getProsjektId(), prosjekt_A);

				System.out.println("Ansatt " + ansatt.getNavn() + " har blitt lagt til prosjekt " + prosjekt.getNavn());
				break;

			case "F":
				System.out.println("Prosjekt id");
				Prosjekt_ADAO.skrivUtProsjekt(Integer.parseInt(sc.nextLine()));

				break;
			case "Q":
				fortsett = false;
				;
				break;
			default:
				System.out.println("Ikke en gyldig key");
			}
			System.out.println("");
			System.out.println("------------------------------");
		}
	}
}
