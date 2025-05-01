package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.DISABLED_ITEM;

public class NameDisabler {

    public static String adjust(String name, boolean enabled) {
        return enabled ? name : DISABLED_ITEM + name;
    }
}

