package com.example.diplom.service;

import com.example.diplom.domain.AdditionalService;
import com.example.diplom.repository.AdditionalServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalServiceService {

    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;

    public List<AdditionalService> getAllAdditionalServices() {
        return additionalServiceRepository.findAll();
    }

    public AdditionalService getAdditionalServiceById(Long id) {
        return additionalServiceRepository.findById(id).orElse(new AdditionalService());
    }

    public AdditionalService createAdditionalService(AdditionalService additionalService) {
        return additionalServiceRepository.save(additionalService);
    }

    public AdditionalService updateAdditionalService(Long id, AdditionalService updatedAdditionalService) {
        if (additionalServiceRepository.existsById(id)) {
            updatedAdditionalService.setServiceId(id);
            return additionalServiceRepository.save(updatedAdditionalService);
        }
        return new AdditionalService();
    }

    public void deleteAdditionalService(Long id) {
        additionalServiceRepository.deleteById(id);
    }
}