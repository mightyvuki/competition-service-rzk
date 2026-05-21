package com.competitions.kotizacija_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "prijava-service")
public interface PrijavaProxy {

    @PutMapping("/prijava/{id}/status")
    void updateStatus(@PathVariable Long id, @RequestParam String status);

}
