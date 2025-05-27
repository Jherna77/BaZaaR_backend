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
        return runScript(DB_SCRIPT);
    }
    
    @Override
    public String backupImages() {
        return runScript(IMG_SCRIPT);
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



