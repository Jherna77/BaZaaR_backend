package com.jhernandez.backend.bazaar.application.port;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.model.Backup;

public interface BackupRepositoryPort {

    void saveBackup(Backup backup);

    List<Backup> findAllBackups();

    Optional<Backup> findBackupById(Long id);

}
