package com.competitions.kotizacija_service.service;

import com.competitions.kotizacija_service.dto.PlacanjeRequest;
import com.competitions.kotizacija_service.exception.EntityAlreadyExistsException;
import com.competitions.kotizacija_service.exception.EntityDoesNotExistException;
import com.competitions.kotizacija_service.exception.EntityNotValidException;
import com.competitions.kotizacija_service.feign.PrijavaProxy;
import com.competitions.kotizacija_service.model.Placanje;
import com.competitions.kotizacija_service.model.Racun;
import com.competitions.kotizacija_service.model.Transakcija;
import com.competitions.kotizacija_service.repository.PlacanjeRepository;
import com.competitions.kotizacija_service.repository.RacunRepository;
import com.competitions.kotizacija_service.repository.TransakcijaRepository;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KotizacijaService {

    private final PlacanjeRepository placanjeRepository;
    private final RacunRepository racunRepository;
    private final TransakcijaRepository transakcijaRepository;

    private final PrijavaProxy prijavaProxy;

    @Transactional
    public Placanje izvrsiPlacanje(PlacanjeRequest request) {
        placanjeRepository.findByPrijavaId(request.getPrijavaId()).
                ifPresent(p -> {throw new EntityAlreadyExistsException("Prijava [id: " + request.getPrijavaId() + "] je već plaćena");});

        Racun racun = racunRepository.findById(request.getRacunId())
                .orElseThrow(() -> new EntityDoesNotExistException("Racun [id: " + request.getRacunId() + "] ne postoji"));

        if (racun.getStanje().compareTo(request.getIznos()) < 0) {
            throw new EntityNotValidException("Nedovoljno sredstava na računu");
        }

        racun.setStanje(racun.getStanje().subtract(request.getIznos()));
        racunRepository.save(racun);

        Transakcija transakcija = new Transakcija();
        transakcija.setRacun(racun);
        transakcija.setIznos(request.getIznos());
        transakcija.setDatum(LocalDateTime.now());
        transakcijaRepository.save(transakcija);

        Placanje placanje = new Placanje();
        placanje.setPrijavaId(request.getPrijavaId());
        placanje.setRacunId(request.getRacunId());
        placanje.setStatus("PAID");

        updatePrijavaStatus(request.getPrijavaId());

        return placanjeRepository.save(placanje);
    }

    public Placanje nadjiPlacanje(Long id) {
        return placanjeRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Placanje [id: " + id + "] ne postoji"));
    }

    public Placanje nadjiPoPrijavi(Long prijavaId) {
        return placanjeRepository.findByPrijavaId(prijavaId)
                .orElseThrow(() -> new EntityDoesNotExistException("Placanje za prijavu [id: " + prijavaId + "] ne postoji"));
    }

    @CircuitBreaker(name = "prijavaCircuitBreaker")
    public void updatePrijavaStatus(Long prijavaId) {
        try {
            prijavaProxy.updateStatus(prijavaId, "APPROVED");
        } catch (FeignException.NotFound e) {
            throw new EntityDoesNotExistException("Prijava [id: " + prijavaId + "] ne postoji");
        }
    }
}
