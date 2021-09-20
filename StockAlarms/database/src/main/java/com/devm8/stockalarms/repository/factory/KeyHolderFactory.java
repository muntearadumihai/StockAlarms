package com.devm8.stockalarms.repository.factory;

import org.springframework.jdbc.support.KeyHolder;

public interface KeyHolderFactory {

    KeyHolder generateKeyHolder();
}
