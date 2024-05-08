package buildWeek;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import buildWeek.DAO.*;
import buildWeek.Entity.*;
import buildWeek.Entity.Manutenzione;
import buildWeek.Enum.*;

public class Main {

    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Benvenuto, quanti biglietti desideri acquistare?");
//        Integer biglietti = scanner.nextInt();
//        scanner.nextLine();
//
//        System.out.println("Da dove parti?");
//        System.out.println("-Napoli \n-Roma \n-Firenze \n-Frosinone \n-Milano");
//        String inizio = scanner.nextLine();
//        System.out.println("Hai scelto di partire da: " + inizio + ". Dove vuoi andare?");
//        System.out.println("-Venezia \n-Bologna \n-Torino \n-Palermo \n-Bari");
//        String arrivo = scanner.nextLine();
//
//        System.out.println("Stai acquistando " + biglietti + " biglietti per un viaggio da " + inizio + " fino a " + arrivo);


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasportopubblico");
        EntityManager em = emf.createEntityManager();

        UtentiDao utentiDao = new UtentiDao(em);
        MezziDao mezziDao = new MezziDao(em);
        TrattaDao trattaDao = new TrattaDao(em);
        ManutenzioneDao manutenzioneDao = new ManutenzioneDao(em);
        ViaggioDao viaggioDao = new ViaggioDao(em);
        BigliettiDao bigliettiDao = new BigliettiDao(em);
        AbbonamentiDao abbonamentiDao = new AbbonamentiDao(em);
        DistributoreAutomaticoDao distributoreAutomaticoDao = new DistributoreAutomaticoDao(em);
        RivenditoreDao rivenditoreDao = new RivenditoreDao(em);

        Utente utente1 = new Utente(Tessera.ABBONATO,"Pippo", "Bianchi");
        Utente utente2 = new Utente(Tessera.DA_RINNOVARE,"Pluto", "Rossi");
        Utente utente3 = new Utente(Tessera.ABBONATO,"Paperino", "Verdi");
        Utente utente4 = new Utente(Tessera.DA_RINNOVARE,"Pietro", "Gambadilegno");
        utentiDao.save(utente1);
        utentiDao.save(utente2);
        utentiDao.save(utente3);
        utentiDao.save(utente4);


        List<Mezzo> mezzi = new ArrayList<>();
        Mezzo mezzo1 = new Mezzo(Servizio.ATTIVO, CapienzaMezzo.DUECENTO, TipoMezzo.AUTOBUS);
        Mezzo mezzo2 = new Mezzo(Servizio.IN_MANUTENZIONE, CapienzaMezzo.SESSANTA, TipoMezzo.TRAM);
        Mezzo mezzo3 = new Mezzo(Servizio.ATTIVO, CapienzaMezzo.DUECENTO, TipoMezzo.TRAM);
        Mezzo mezzo4 = new Mezzo(Servizio.IN_MANUTENZIONE, CapienzaMezzo.SESSANTA, TipoMezzo.AUTOBUS);
        Mezzo mezzo5 = new Mezzo(Servizio.ATTIVO, CapienzaMezzo.SESSANTA, TipoMezzo.AUTOBUS);
        mezzi.add(mezzo1);
        mezzi.add(mezzo2);
        mezzi.add(mezzo3);
        mezzi.add(mezzo4);
        mezzi.add(mezzo5);
        mezziDao.save(mezzi);


        List<Manutenzione> manutenzioni = new ArrayList<>();
        Manutenzione manutenzione1 = new Manutenzione(LocalDate.of(2024, 1, 20), LocalDate.of(2024, 2, 25), mezzo1);
        Manutenzione manutenzione2 = new Manutenzione(LocalDate.of(2024, 2, 27), LocalDate.of(2024, 3, 25), mezzo4);
        manutenzioni.add(manutenzione1);
        manutenzioni.add(manutenzione2);
        manutenzioneDao.save(manutenzioni);



        Tratta tratta1 = new Tratta("Roma","Napoli",40,mezzi);
        Tratta tratta2 = new Tratta("Trieste","Trapani",1200,mezzi);
        Tratta tratta3 = new Tratta("Firenze", "Bari", 600, mezzi);
        Tratta tratta4 = new Tratta("Anagni", "Trani", 540, mezzi);
        Tratta tratta5 = new Tratta("Napoli", "Caserta", 40, mezzi);
        Tratta tratta6 = new Tratta("Roma", "Teramo", 120, mezzi);
        List<Tratta> tratte = new ArrayList<>(List.of(tratta1, tratta2, tratta3, tratta4, tratta5, tratta6));
        trattaDao.save(tratte);


        List<Biglietto> listaTotale = new ArrayList<>();
        List<DistributoreAutomatico> distributori = new ArrayList<>();
        DistributoreAutomatico distributore1 = new DistributoreAutomatico(StatoDistributori.ATTIVO, listaTotale);
        distributori.add(distributore1);
        distributoreAutomaticoDao.save(distributori);



        List<Rivenditore> rivenditori = new ArrayList<>();
        Rivenditore rivenditore1 = new Rivenditore("Atac", "Piazza Conca d'Oro", listaTotale);


        rivenditori.add(rivenditore1);
        rivenditoreDao.save(rivenditori);


        Biglietto biglietto1 = new Biglietto(false, LocalTime.of(10,20,25),LocalTime.of(11,50,55), utente1, tratta1, distributore1);
        Biglietto biglietto2 = new Biglietto(true, LocalTime.of(12,23,22), LocalTime.of(13,50,55), utente2, tratta5, distributore1);
        Biglietto biglietto3 = new Biglietto(false, LocalTime.of(20,50,11), LocalTime.of(22,10,12), utente3, tratta2, distributore1);
        Biglietto biglietto4 = new Biglietto(true, LocalTime.of(12,23,22), LocalTime.of(13,50,55), utente4, tratta3, rivenditore1);
        Biglietto biglietto5 = new Biglietto(false, LocalTime.of(20,50,11), LocalTime.of(22,10,12), utente2, tratta4, rivenditore1);
        bigliettiDao.save(biglietto1);
        bigliettiDao.save(biglietto2);
        bigliettiDao.save(biglietto3);
        bigliettiDao.save(biglietto4);
        bigliettiDao.save(biglietto5);

        listaTotale.add(biglietto1);


        Viaggio viaggio1 = new Viaggio( LocalTime.of(10,20,25), LocalTime.of(12,20,25), biglietto1, mezzo1);
        Viaggio viaggio2 = new Viaggio( LocalTime.of(12,25, 25), LocalTime.of(13, 5,15), biglietto2, mezzo5);
        viaggioDao.save(viaggio1);
        viaggioDao.save(viaggio2);


        Abbonamento abbonamento1 = new Abbonamento(Stato.ATTIVO, DurataAbbonamento.ANNUALE, utente1);
        Abbonamento abbonamento2 = new Abbonamento(Stato.ATTIVO, DurataAbbonamento.MENSILE, utente3);
        Abbonamento abbonamento3 = new Abbonamento(Stato.NON_ATTIVO, DurataAbbonamento.ANNUALE, utente2);

        abbonamentiDao.save(abbonamento1);
        abbonamentiDao.save(abbonamento2);
        abbonamentiDao.save(abbonamento3);













//        Tratta tratta2 = new Tratta("Frosinone","Messina",200);
//        TrattaDao trattaDao = new TrattaDao(em);
//         trattaDao.save(tratta1);
//          trattaDao.save(tratta2);
    }

}
