package org.lessons.java.spring_la_mia_pizzeria_security.services;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_security.models.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_security.models.Offer;
import org.lessons.java.spring_la_mia_pizzeria_security.models.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_security.repositories.IngredientRepository;
import org.lessons.java.spring_la_mia_pizzeria_security.repositories.OfferRepository;
import org.lessons.java.spring_la_mia_pizzeria_security.repositories.PizzaRepository;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
    private PizzaRepository pizzaRepository;
    private OfferRepository offerRepository;
    private IngredientRepository ingredientRepository;

    public PizzaService(
            PizzaRepository pizzaRepository,
            OfferRepository offerRepository,
            IngredientRepository ingredientRepository) {
        this.pizzaRepository = pizzaRepository;
        this.offerRepository = offerRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Optional<Pizza> findById(Integer id) {
        return pizzaRepository.findById(id);
    }

    public Pizza getById(Integer id) {
        Optional<Pizza> pizza = findById(id);
        if (pizza.isEmpty()) {
            // throw new NameNotFoundException("Pizza not found");
        }
        return pizza.get();
    }

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public List<Pizza> findByNameContaining(String name) {
        return pizzaRepository.findByNameContaining(name);
    }

    public List<Pizza> findAllOrByNameContaining(String name) {
        List<Pizza> list;

        if (name == null) {
            list = findAll();
        } else {
            list = findByNameContaining(name);
        }
        return list;
    }

    public Boolean existsById(Integer id) {
        return pizzaRepository.existsById(id);
    }

    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza edit(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void deleteById(Integer id) {
        Pizza pizza = getById(id);
        for (Offer offer : pizza.getOffers()) {
            offerRepository.delete(offer);
        }
        pizzaRepository.delete(pizza);
    }

    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }
}
