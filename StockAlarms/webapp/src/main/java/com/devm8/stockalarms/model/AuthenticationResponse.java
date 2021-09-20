package com.devm8.stockalarms.model;

import java.util.Objects;

public class AuthenticationResponse {

    private String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public AuthenticationResponse() {
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthenticationResponse)) return false;
        AuthenticationResponse that = (AuthenticationResponse) o;
        return Objects.equals(getJwt(), that.getJwt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJwt());
    }
}
