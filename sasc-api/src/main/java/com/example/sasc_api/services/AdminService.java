package com.example.sasc_api.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.sasc_api.domain.Admin;
import com.example.sasc_api.repositories.AdminRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AdminService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    AdminRepository adminRepository;
    
    public void registerSalaFromJson() throws Exception {
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("Starting the registration of products from the JSON file");
    
        ClassPathResource resource = new ClassPathResource("admin.json");
    
        try (InputStream inputStream = resource.getInputStream()) {
            List<Admin> admins = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<Admin>>() {
                    });
            adminRepository.saveAll(admins);
            logger.info("Product registration completed successfully: {} rooms registered.", admins.size());
        } catch (IOException e) {
            logger.error("Error loading the JSON file", e);
            throw new RuntimeException("Error loading the JSON file", e);
        
        }
    }
}
