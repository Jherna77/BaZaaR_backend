package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.BackupRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.BackupServicePort;
import com.jhernandez.backend.bazaar.application.port.BackupStoragePort;
import com.jhernandez.backend.bazaar.domain.exception.BackupException;
import com.jhernandez.backend.bazaar.domain.exception.ErrorCode;
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

    @Override
    public List<Backup> findAllBackups() {
        return backupRepository.findAllBackups();
    }

    @Override
    public Backup findBackupById(Long id) throws BackupException {
        if (id == null) {
            throw new BackupException(ErrorCode.BACKUP_ID_NOT_NULL);
        }
        return backupRepository.findBackupById(id)
                .orElseThrow(() -> new BackupException(ErrorCode.BACKUP_NOT_FOUND));
    }

    @Override
    public void restoreBackup(Long id) throws BackupException {
        if (id == null) {
            throw new BackupException(ErrorCode.BACKUP_ID_NOT_NULL);
        }
        Backup backup = findBackupById(id);
        backupStorage.restoreDatabase(backup.getDataBaseFileName());
        backupStorage.restoreImages(backup.getImagesFileName());
    }
}
