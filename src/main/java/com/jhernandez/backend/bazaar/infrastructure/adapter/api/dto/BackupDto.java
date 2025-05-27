package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BackupDto {

    Long id;
    String createdAt;
    String dataBaseFileName;
    String imagesFileName;

}
