package org.lessons.java.spring_la_mia_pizzeria_webapi.services;

import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_webapi.models.Offer;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repositories.OfferRepository;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    private OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Optional<Offer> findById(Integer id) {
        return offerRepository.findById(id);
    }

    public Offer getById(Integer id) {
        Optional<Offer> offer = findById(id);
        if (offer.isEmpty()) {
            // throw new NameNotFoundException("Offer not found");
        }

        return offer.get();
    }

    public Boolean existsById(Integer id) {
        return offerRepository.existsById(id);
    }

    public Offer save(Offer offer) {
        return offerRepository.save(offer);
    }

    public Offer edit(Offer offer) {
        return offerRepository.save(offer);
    }

    public void deleteById(Integer id) {
        offerRepository.deleteById(id);
    }
}
