package org.lessons.java.spring_la_mia_pizzeria_webapi.controllers;

import org.lessons.java.spring_la_mia_pizzeria_webapi.models.Offer;
import org.lessons.java.spring_la_mia_pizzeria_webapi.services.OfferService;
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
@RequestMapping("/offers")
public class OfferController {
    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Integer offerId, Model model) {
        if (!offerService.existsById(offerId)) {
            return "redirect:/pizzas";
        }
        model.addAttribute("offer", offerService.getById(offerId));
        return "offers/show";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offer") Offer offerForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "offers/create";
        }
        Offer offer = offerService.save(offerForm);
        return "redirect:/pizzas/" + offer.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer offerId, Model model) {
        if (!offerService.existsById(offerId)) {
            return "redirect:/pizzas";
        }
        model.addAttribute("offer", offerService.getById(offerId));
        return "/offers/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offer") Offer offerForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "/offers/edit";
        }
        Offer offer = offerService.edit(offerForm);
        return "redirect:/pizzas/" + offer.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer offerId, Model model) {
        offerService.deleteById(offerId);
        return "redirect:/pizzas/" + offerService.getById(offerId).getPizza().getId();
    }

}
