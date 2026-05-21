package com.competitions.prijava_service.feign;

import com.competitions.prijava_service.dto.KorisnikDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "korisnik-service")
public interface KorisnikProxy {

    @GetMapping("/korisnici/{id}")
    ResponseEntity<KorisnikDTO> korisnik(@PathVariable("id") Long id);
}
