package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.jhernandez.backend.bazaar.application.port.BackupServicePort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.BACKUP;

@RestController
@RequestMapping(BACKUP)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class BackupController {

    private final BackupServicePort backupService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    /**
     * Endpoint to create a backup of the application data.
     * Only accessible by users with the ADMIN role.
     *
     * @return ResponseEntity indicating the status of the backup creation.
     */
    public ResponseEntity<?> createBackup() {
        log.info("Creating backup");
        backupService.createBackup();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // @PostMapping("/database")
    // public ResponseEntity<Map<String, String>> backupDatabase() {
    //     String filePath = backupService.backupDatabase();
    //     return ResponseEntity.ok(Map.of("status", "OK", "filePath", filePath));
    // }

    // @PostMapping("/images")
    // public ResponseEntity<Map<String, String>> backupImages() {
    //     String filePath = backupService.backupImages();
    //     return ResponseEntity.ok(Map.of("status", "OK", "filePath", filePath));
    // }
}
