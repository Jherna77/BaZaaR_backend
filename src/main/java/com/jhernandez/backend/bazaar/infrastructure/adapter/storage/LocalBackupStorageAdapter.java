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

    private static final String DB_SCRIPT = "/bazaar/backup_db.sh";
    private static final String IMG_SCRIPT = "/bazaar/backup_images.sh";

    @Override
    public String backupDatabase() {
        log.info("Starting database backup using script: {}", DB_SCRIPT);
        return runScript(DB_SCRIPT);
    }
    
    @Override
    public String backupImages() {
        log.info("Starting images backup using script: {}", IMG_SCRIPT);
        return runScript(IMG_SCRIPT);
    }

    @Override
    public void restoreDatabase(String databaseFileName) throws BackupException {
        log.info("Restoring database from file: {}", databaseFileName);
        try {
            // ProcessBuilder builder = new ProcessBuilder("bash", "-c", "mysql -u root -p bazaar < " + databaseFileName);
            // builder.redirectErrorStream(true);
            // Process process = builder.start();
            // process.waitFor();
        } catch (Exception e) {
            throw new BackupException(ErrorCode.BACKUP_RESTORE_ERROR);
        }
    }
    @Override
    public void restoreImages(String imagesFileName) throws BackupException {
        log.info("Restoring images from file: {}", imagesFileName);
        try {
            // ProcessBuilder builder = new ProcessBuilder("bash", "-c", "cp -r " + imagesFileName + " /path/to/images/directory");
            // builder.redirectErrorStream(true);
            // Process process = builder.start();
            // process.waitFor();
        } catch (Exception e) {
            throw new BackupException(ErrorCode.BACKUP_RESTORE_ERROR);
        }
    }

    private String runScript(String scriptPath) throws BackupException {
        try {
            ProcessBuilder builder = new ProcessBuilder("bash", scriptPath);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String outputPath = reader.readLine();
            process.waitFor();

            if (outputPath == null || outputPath.isBlank()) {
                throw new BackupException(ErrorCode.BACKUP_PATH_ERROR);
            }

            return outputPath;
        } catch (Exception e) {
            throw new BackupException(ErrorCode.BACKUP_SCRIPT_ERROR);
        }
    }

}



