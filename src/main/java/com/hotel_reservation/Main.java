package com.hotel_reservation;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/**
 * The main class for the hotel reservation application.
 */
public final class Main {

    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private Main() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * The entry point of the application.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(final String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

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
