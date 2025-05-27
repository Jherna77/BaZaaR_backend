package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.model.Backup;

public interface BackupRepositoryPort {

    void saveBackup(Backup backup);

}
