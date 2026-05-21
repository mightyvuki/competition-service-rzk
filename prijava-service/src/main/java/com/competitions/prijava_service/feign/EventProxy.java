package com.competitions.prijava_service.feign;

import com.competitions.prijava_service.dto.DogadjajDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "event-service")
public interface EventProxy {
    @GetMapping("/events/{id}")
    ResponseEntity<DogadjajDTO> dogadjaj(@PathVariable("id") Long id);
}
