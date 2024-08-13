package com.example.employeemanagementsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Here, you can fetch the current user, for example, from Spring Security context
        // For simplicity, let's assume a static user for now
        return Optional.of("System");
    }
}
