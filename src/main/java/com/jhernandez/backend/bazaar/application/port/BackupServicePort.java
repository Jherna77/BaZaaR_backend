package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.CreateBackupUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.RetrieveBackupUseCase;

public interface BackupServicePort extends CreateBackupUseCase, RetrieveBackupUseCase{

}
