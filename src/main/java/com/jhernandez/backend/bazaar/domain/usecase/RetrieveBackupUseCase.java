package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.BackupException;
import com.jhernandez.backend.bazaar.domain.model.Backup;

public interface RetrieveBackupUseCase {

    List<Backup> findAllBackups();

    Backup findBackupById(Long id) throws BackupException;

    void restoreBackup(Long id) throws BackupException;

}
