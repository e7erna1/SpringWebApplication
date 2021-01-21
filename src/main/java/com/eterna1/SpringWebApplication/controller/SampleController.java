package com.eterna1.SpringWebApplication.controller;

import com.eterna1.SpringWebApplication.domain.application.SampleData;
import com.eterna1.SpringWebApplication.repository.application.SampleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    Logger logger = LogManager.getLogger("common");

    private final SampleRepository sampleRepository;

    public SampleController(
        SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @GetMapping("/")
    public SampleData getSomeData() {
        logger.info("Some Info");
        return sampleRepository.findById(1L).orElse(null);
    }
}
