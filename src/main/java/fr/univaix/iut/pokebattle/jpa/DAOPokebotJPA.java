package fr.univaix.iut.pokebattle.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;


import java.util.List;

public class DAOPokebotJPA implements DAOPokebot {

    private EntityManager entityManager;

    public DAOPokebotJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean delete(Pokebot obj) {
        try {
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            entityManager.remove(obj);
            tx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Pokebot> findAll() {
        TypedQuery<Pokebot> query = entityManager.createNamedQuery(Pokebot.FIND_ALL, Pokebot.class);
        return query.getResultList();
    }

    @Override
    public Pokebot getById(String id) {
        return entityManager.find(Pokebot.class, id);
    }

    @Override
    public Pokebot insert(Pokebot obj) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(obj);
        tx.commit();
        return entityManager.find(Pokebot.class, obj.getName());
    }

    @Override
    public boolean update(Pokebot obj) {
        try {
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(obj);
            tx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}