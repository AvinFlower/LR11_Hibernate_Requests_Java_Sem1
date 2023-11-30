package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создаем фабрику сессий
        SessionFactory factory = new Configuration()
                .configure("hibernate_configuration.xml")
                .addAnnotatedClass(Concert.class)
                .addAnnotatedClass(CountryCity.class)
                .addAnnotatedClass(Venue.class)
                .buildSessionFactory();

        Requests requests = new Requests(factory);

        CountryCity city1 = new CountryCity("Los Angeles", "USA");
        requests.addCountryCity(city1);
        CountryCity city2 = new CountryCity("New York", "USA");
        requests.addCountryCity(city2);
        CountryCity city3 = new CountryCity("Toronto", "Canada");
        requests.addCountryCity(city3);
        CountryCity city4 = new CountryCity("Paris", "France");
        requests.addCountryCity(city4);


        Venue venue1 = new Venue("Dodger Stadium", 50000, city1);
        requests.addVenue(venue1);
        Venue venue2 = new Venue("Madison Square Garden", 20000, city2);
        requests.addVenue(venue2);
        Venue venue3 = new Venue("Terminal 5", 70000, city2);
        requests.addVenue(venue3);
        Venue venue4 = new Venue("Enigma", 14000, city4);
        requests.addVenue(venue4);

        Concert concert1 = new Concert("Concert A", Date.valueOf("2023-02-01"), venue1);
        requests.addConcert(concert1);
        Concert concert2 = new Concert("IGOR CONCERT", Date.valueOf("2023-11-15"), venue2);
        requests.addConcert(concert2);
        Concert concert3 = new Concert("IGOR CONCERT", Date.valueOf("2024-01-10"), venue2);
        requests.addConcert(concert3);
        Concert concert4 = new Concert("Everything But The Girl", Date.valueOf("2024-11-27"), venue3);
        requests.addConcert(concert4);


        //SELECT 1(Выборка всех концертов по ID страны и города)
        requests.printConcertsByCityId(1L);

//        //SELECT 2(Выборка всех городов и стран по имени концерта)
//        requests.printCitiesByConcertName("IGOR CONCERT");
//
//        //DELETE(Каскадное удаление по ID страны и города)
//        requests.deleteConcertsAndVenuesByCityId(1L);
//
//        //UPDATE(Изменение названия концерта по ID)
//        requests.updateConcertNameById(1L, "New Concert Name");
//
//        //INSERT(Вставка города и страны)
//        requests.addCountryCity("London", "United Kingdom");
    }
}