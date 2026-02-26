package org.lessons.java.spring_la_mia_pizzeria_security.controllers;

import org.lessons.java.spring_la_mia_pizzeria_security.models.Offer;
import org.lessons.java.spring_la_mia_pizzeria_security.models.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_security.services.PizzaService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    private PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public String index(
            Model model,
            @RequestParam(value = "q", required = false) String name) {
        model.addAttribute("pizzas", pizzaService.findAllOrByNameContaining(name));
        model.addAttribute("query", name);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer pizzaId) {
        if (!pizzaService.existsById(pizzaId)) {
            return "redirect:/pizzas";
        }

        Pizza pizza = pizzaService.getById(pizzaId);
        model.addAttribute("pizza", pizza);
        model.addAttribute("offers", pizza.getOffers());
        model.addAttribute("ingredients", pizza.getIngredients());
        return "pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", pizzaService.findAllIngredients());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", pizzaService.findAllIngredients());
            return "/pizzas/create";
        }

        pizzaService.save(pizzaForm);
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer pizzaId) {
        model.addAttribute("ingredients", pizzaService.findAllIngredients());
        if (!pizzaService.existsById(pizzaId)) {
            model.addAttribute("pizza", new Pizza());
            return "redirect:/pizzas/create";
        }
        model.addAttribute("pizza", pizzaService.getById(pizzaId));
        return "/pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", pizzaService.findAllIngredients());
            return "/pizzas/edit";
        }
        pizzaService.edit(pizzaForm);
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Integer pizzaId) {
        pizzaService.deleteById(pizzaId);
        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/create-offer")
    public String createOffer(@PathVariable("id") Integer pizzaId, Model model) {
        if (!pizzaService.existsById(pizzaId)) {
            return "redirect:/pizzas";
        }
        Offer newOffer = new Offer();
        newOffer.setPizza(pizzaService.getById(pizzaId));
        model.addAttribute("offer", newOffer);
        return "offers/create";
    }

}
