package com.jhernandez.backend.bazaar.infrastructure.adapter.storage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.application.port.BackupStoragePort;
import com.jhernandez.backend.bazaar.domain.exception.BackupException;
import com.jhernandez.backend.bazaar.domain.exception.ErrorCode;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LocalBackupStorageAdapter implements BackupStoragePort {

    private static final String DB_BACKUP_SCRIPT = "/bazaar/backup_db.sh";
    private static final String IMG_BACKUP_SCRIPT = "/bazaar/backup_images.sh";
    private static final String DB_RESTORE_SCRIPT = "/bazaar/restore_db.sh";
    private static final String IMG_RESTORE_SCRIPT = "/bazaar/restore_images.sh";

    @Override
    public void backupDatabase(String databaseFilePath) throws BackupException {
        log.info("Starting database backup to: {}", databaseFilePath);
        runScript(DB_BACKUP_SCRIPT, databaseFilePath);
    }

    @Override
    public void backupImages(String imagesFilePath) throws BackupException {
        log.info("Starting images backup to: {}", imagesFilePath);
        runScript(IMG_BACKUP_SCRIPT, imagesFilePath);
    }

    @Override
    public void restoreDatabase(String databaseFilePath) throws BackupException {
        log.info("Restoring database from: {}", databaseFilePath);
        runRestoreScript(DB_RESTORE_SCRIPT, databaseFilePath);
    }

    @Override
    public void restoreImages(String imagesFilePath) throws BackupException {
        log.info("Restoring images from: {}", imagesFilePath);
        runRestoreScript(IMG_RESTORE_SCRIPT, imagesFilePath);
    }

    private void runScript(String scriptPath, String outputPath) throws BackupException {
        try {
            ProcessBuilder builder = new ProcessBuilder("bash", scriptPath, outputPath);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            process.waitFor();
            log.info("Backup creado en {}", outputPath);
        } catch (Exception e) {
            throw new BackupException(ErrorCode.BACKUP_SCRIPT_ERROR);
        }
    }

    private void runRestoreScript(String scriptPath, String filePath) throws BackupException {
        try {
            ProcessBuilder builder = new ProcessBuilder("bash", scriptPath, filePath);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                log.info("SCRIPT: {}", line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new BackupException(ErrorCode.BACKUP_RESTORE_ERROR);
            }
        } catch (Exception e) {
            throw new BackupException(ErrorCode.BACKUP_RESTORE_ERROR);
        }
    }

}
