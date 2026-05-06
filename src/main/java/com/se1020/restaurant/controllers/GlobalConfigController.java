package com.se1020.restaurant.controllers;

import com.se1020.restaurant.models.GlobalConfig;
import com.se1020.restaurant.repositories.GlobalConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
public class GlobalConfigController {

    @Autowired
    private GlobalConfigRepository globalConfigRepository;

    @GetMapping
    public ResponseEntity<GlobalConfig> getConfig() {
        GlobalConfig config = globalConfigRepository.findById(1L).orElseGet(() -> {
            GlobalConfig newConfig = new GlobalConfig();
            return globalConfigRepository.save(newConfig);
        });
        return ResponseEntity.ok(config);
    }

    @PutMapping
    public ResponseEntity<GlobalConfig> updateConfig(@RequestBody GlobalConfig updatedConfig) {
        GlobalConfig config = globalConfigRepository.findById(1L).orElse(new GlobalConfig());
        config.setMinBookingHours(updatedConfig.getMinBookingHours());
        config.setMaxBookingHours(updatedConfig.getMaxBookingHours());

        if (updatedConfig.getAreaMinBookingHours() > 0) config.setAreaMinBookingHours(updatedConfig.getAreaMinBookingHours());
        if (updatedConfig.getAreaMaxBookingHours() > 0) config.setAreaMaxBookingHours(updatedConfig.getAreaMaxBookingHours());

        // New configuration parameters
        if (updatedConfig.getTableCancelHours() > 0) config.setTableCancelHours(updatedConfig.getTableCancelHours());
        if (updatedConfig.getAreaCancelHours() > 0) config.setAreaCancelHours(updatedConfig.getAreaCancelHours());
        if (updatedConfig.getRestaurantOpenTime() > 0) config.setRestaurantOpenTime(updatedConfig.getRestaurantOpenTime());
        if (updatedConfig.getRestaurantCloseTime() > 0) config.setRestaurantCloseTime(updatedConfig.getRestaurantCloseTime());

        if (updatedConfig.getTableAdvanceBookingDays() > 0) config.setTableAdvanceBookingDays(updatedConfig.getTableAdvanceBookingDays());
        if (updatedConfig.getAreaAdvanceBookingDays() > 0) config.setAreaAdvanceBookingDays(updatedConfig.getAreaAdvanceBookingDays());
        if (updatedConfig.getAreaMinAdvanceBookingDays() >= 0) config.setAreaMinAdvanceBookingDays(updatedConfig.getAreaMinAdvanceBookingDays());

        GlobalConfig savedConfig = globalConfigRepository.save(config);
        return ResponseEntity.ok(savedConfig);
    }
}
