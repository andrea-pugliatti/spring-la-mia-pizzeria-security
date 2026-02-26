package org.lessons.java.spring_la_mia_pizzeria_security.repositories;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_security.models.Offer;
import org.lessons.java.spring_la_mia_pizzeria_security.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    public List<Offer> findByPizza(Pizza pizza);
}
