package com.devm8.stockalarms.repository.factory;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class KeyHolderFactoryImpl implements KeyHolderFactory {

    @Override
    public KeyHolder generateKeyHolder() {
        return new GeneratedKeyHolder();
    }
}
