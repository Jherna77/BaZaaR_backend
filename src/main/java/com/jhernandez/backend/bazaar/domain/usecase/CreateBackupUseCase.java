package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.BackupException;

public interface CreateBackupUseCase {

    void createBackup() throws BackupException;

}
