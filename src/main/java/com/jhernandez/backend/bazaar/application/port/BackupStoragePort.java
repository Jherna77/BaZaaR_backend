package com.jhernandez.backend.bazaar.application.port;

public interface BackupStoragePort {

    String backupDatabase();

    String backupImages();

    void restoreDatabase(String databaseFileName);
    
    void restoreImages(String imagesFileName);

}
