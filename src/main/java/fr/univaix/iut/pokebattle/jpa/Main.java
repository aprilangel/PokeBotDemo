package fr.univaix.iut.pokebattle.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class Main {

    static void main(String[] args) {
        // Initializes the Entity manager

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokebattlePU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Pokebot pikabot = new Pokebot("PikachuShiny");
        pikabot.setEspece("Pikachu");
        em.persist(pikabot);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}