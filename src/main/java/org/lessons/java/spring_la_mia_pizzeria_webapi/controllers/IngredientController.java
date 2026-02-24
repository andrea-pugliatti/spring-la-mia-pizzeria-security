package org.lessons.java.spring_la_mia_pizzeria_webapi.controllers;

import org.lessons.java.spring_la_mia_pizzeria_webapi.models.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_webapi.services.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientService.findAll());
        model.addAttribute("new_ingredient", new Ingredient());
        return "ingredients/index";
    }

    @PostMapping("/create")
    public String store(
            @Valid @ModelAttribute("new_ingredient") Ingredient ingredientForm,
            BindingResult bindingResult,
            Model model) {
        if (!bindingResult.hasErrors()) {
            ingredientService.save(ingredientForm);
            model.addAttribute("new_ingredient", new Ingredient());
        }
        model.addAttribute("ingredients", ingredientService.findAll());
        return "ingredients/index";
    }

    @PostMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Integer ingredientId) {
        ingredientService.deleteById(ingredientId);
        return "redirect:/ingredients";
    }

}
