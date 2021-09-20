package com.devm8.stockalarms.model;

public class AuthenticatedUser {

    private int userId;
    private String email;

    public AuthenticatedUser(AuthenticatedUserDTOBuilder builder) {
        this.userId = builder.userId;
        this.email = builder.email;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }


    public static class AuthenticatedUserDTOBuilder {

        private int userId;
        private String email;

        public AuthenticatedUserDTOBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public AuthenticatedUserDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public AuthenticatedUser build() {
            return new AuthenticatedUser(this);
        }
    }
}
