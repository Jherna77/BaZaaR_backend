package com.jhernandez.backend.bazaar.application.service;

import com.jhernandez.backend.bazaar.application.port.BackupRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.BackupServicePort;
import com.jhernandez.backend.bazaar.application.port.BackupStoragePort;
import com.jhernandez.backend.bazaar.domain.exception.BackupException;
import com.jhernandez.backend.bazaar.domain.model.Backup;

public class BackupService implements BackupServicePort {

    private final BackupStoragePort backupStorage;
    private final BackupRepositoryPort backupRepository;

    public BackupService(BackupStoragePort backupStorage, BackupRepositoryPort backupRepository) {
        this.backupStorage = backupStorage;
        this.backupRepository = backupRepository; 
    }

    @Override
    public void createBackup() throws BackupException {
        String databaseFileName = backupStorage.backupDatabase();
        String imagesFileName = backupStorage.backupImages();
        Backup backup = new Backup(databaseFileName, imagesFileName);
        backupRepository.saveBackup(backup);
    }
}
