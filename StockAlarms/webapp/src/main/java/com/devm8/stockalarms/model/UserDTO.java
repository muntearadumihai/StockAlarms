package com.devm8.stockalarms.model;

import java.util.Objects;

public class UserDTO {

    private int userId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    private UserDTO(UserDTOBuilder userDTOBuilder) {
        this.userId = userDTOBuilder.userId;
        this.firstName = userDTOBuilder.firstName;
        this.lastName = userDTOBuilder.lastName;
        this.password = userDTOBuilder.password;
        this.email = userDTOBuilder.email;
    }

    public UserDTO() {
    }

    public int getUserId() {
        return userId;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return getUserId() == userDTO.getUserId() && Objects.equals(getFirstName(),
                userDTO.getLastName()) && Objects.equals(getLastName(), userDTO.getLastName())
                && Objects.equals(getPassword(), userDTO.getPassword()) &&
                Objects.equals(getEmail(), userDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getFirstName(), getLastName(), getPassword(), getEmail());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class UserDTOBuilder {
        private int userId;
        private String firstName;
        private String lastName;
        private String password;
        private String email;

        public UserDTOBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public UserDTOBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDTOBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }
}
