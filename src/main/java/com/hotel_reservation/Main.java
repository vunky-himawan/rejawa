package com.hotel_reservation;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        try {
            System.out.println("Running");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();

            System.out.println("Closing SessionFactory");
        }
    }
}