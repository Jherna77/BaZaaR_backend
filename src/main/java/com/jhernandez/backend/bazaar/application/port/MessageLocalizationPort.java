package com.jhernandez.backend.bazaar.application.port;

import java.util.Map;

public interface MessageLocalizationPort {

    String getMessage(String key, Map<String, Object> args);

}
