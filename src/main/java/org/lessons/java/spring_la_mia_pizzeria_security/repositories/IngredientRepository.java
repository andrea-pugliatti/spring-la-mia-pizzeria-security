package org.lessons.java.spring_la_mia_pizzeria_security.repositories;

import org.lessons.java.spring_la_mia_pizzeria_security.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
