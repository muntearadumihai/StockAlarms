package com.devm8.stockalarms.model;

import java.util.Objects;

public class UserDO {

    private int userId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    private UserDO(UserDOBuilder userDOBuilder) {
        this.userId = userDOBuilder.userId;
        this.firstName = userDOBuilder.firstName;
        this.lastName = userDOBuilder.lastName;
        this.password = userDOBuilder.password;
        this.email = userDOBuilder.email;
    }

    public UserDO() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDO userDO = (UserDO) o;
        return getUserId() == userDO.getUserId() && getFirstName().equals(userDO.getFirstName()) &&
                getLastName().equals(userDO.getLastName()) && getPassword().equals(userDO.getPassword()) &&
                getEmail().equals(userDO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getFirstName(), getLastName(), getPassword(), getEmail());
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class UserDOBuilder {
        private int userId;
        private String firstName;
        private String lastName;
        private String password;
        private String email;

        public UserDOBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public UserDOBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDOBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserDOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDO build() {
            return new UserDO(this);
        }
    }
}
