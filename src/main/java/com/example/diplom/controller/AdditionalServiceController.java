package com.example.diplom.controller;

import com.example.diplom.domain.AdditionalService;
import com.example.diplom.service.AdditionalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/additional-services")
public class AdditionalServiceController {

    @Autowired
    private AdditionalServiceService additionalServiceService;

    @GetMapping
    public List<AdditionalService> getAllAdditionalServices() {
        return additionalServiceService.getAllAdditionalServices();
    }

    @GetMapping("/{id}")
    public AdditionalService getAdditionalServiceById(@PathVariable Long id) {
        return additionalServiceService.getAdditionalServiceById(id);
    }

    @PostMapping
    public AdditionalService createAdditionalService(@RequestBody AdditionalService additionalService) {
        return additionalServiceService.createAdditionalService(additionalService);
    }

    @PutMapping("/{id}")
    public AdditionalService updateAdditionalService(@PathVariable Long id, @RequestBody AdditionalService updatedAdditionalService) {
        return additionalServiceService.updateAdditionalService(id, updatedAdditionalService);
    }

    @DeleteMapping("/{id}")
    public void deleteAdditionalService(@PathVariable Long id) {
        additionalServiceService.deleteAdditionalService(id);
    }
}
