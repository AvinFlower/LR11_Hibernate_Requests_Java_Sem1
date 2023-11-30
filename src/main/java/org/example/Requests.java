package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//import jakarta.persistence.*;
import java.util.*;

public class Requests {
    private final SessionFactory sessionFactory;

    public Requests(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addCountryCity(CountryCity countryCity){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(countryCity);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void addVenue(Venue venue){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(venue);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void addConcert(Concert concert){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(concert);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void printConcertsByCityId(Long cityId) {
        try (Session session = sessionFactory.openSession()) {
            CountryCity city = session.get(CountryCity.class, cityId);

            if (city != null) {
                System.out.println(city.getCity());
                for (Venue venue : city.getVenues()) {
                    for (Concert concert : venue.getConcerts()) {
                        System.out.println(concert);
                    }
                }
            } else {
                System.out.println("City not found with ID: " + "#"+ cityId + "#");
            }
        }
    }



    public void printCitiesByConcertName(String concertName) {
        try (Session session = sessionFactory.openSession()) {
            // Находим все концерты по названию
            List<Concert> concerts = session.createQuery("FROM Concert c WHERE c.concertName = :concertName")
                    .setParameter("concertName", concertName)
                    .list();

            for (Concert concert : concerts) {
                if (concert.getVenue() != null) {
                    // Печатаем город из места проведения концерта
                    System.out.println(concert.getVenue().getCountryCity());
                }
            }
        }
    }


    public void deleteConcertsAndVenuesByCityId(Long cityId) {
        System.out.println("CASCADE DELETED:");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            CountryCity city = session.get(CountryCity.class, cityId);

            if (city != null) {

                for (Venue venue : city.getVenues()) {
                    for (Concert concert : venue.getConcerts()) {
                        System.out.println(concert);
                        session.delete(concert);
                    }
                }

                for (Venue venue : city.getVenues()) {
                    System.out.println(venue);
                    session.delete(venue);
                }

                System.out.println(city);
                session.delete(city);

                transaction.commit();
            } else {
                System.out.println("City not found with ID: " + cityId);
            }
        }
    }


    public void updateConcertNameById(Long concertId, String newConcertName) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Concert concert = session.get(Concert.class, concertId);

            if (concert != null) {
                System.out.println("Updating concert: " + concert);

                concert.setConcertName(newConcertName);

                session.update(concert);

                transaction.commit();
            } else {
                System.out.println("Concert not found with ID: " + concertId);
            }
        }
    }


    public void addCountryCity(String city, String country) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            CountryCity countryCity = new CountryCity(city, country);

            System.out.println("Adding: " + countryCity);

            session.persist(countryCity);

            transaction.commit();
        }
    }

}