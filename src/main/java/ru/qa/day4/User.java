package ru.qa.day4;

import java.util.Objects;

/**
 * Represents a user with ID, name, email, and age
 */
public class User {
    private final int id;
    private final String name;
    private final String email;
    private final int age;

    /**
     * Constructs a new User
     * @param id the user ID
     * @param name the user name
     * @param email the user email
     * @param age the user age
     */
    public User(int id, String name, String email, int age) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && 
               age == user.age && 
               Objects.equals(name, user.name) && 
               Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age);
    }
    
    /**
     * Creates a new User.Builder instance
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * Builder class for User objects
     */
    public static class Builder {
        private int id;
        private String name;
        private String email;
        private int age;
        
        public Builder id(int id) {
            this.id = id;
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        
        public User build() {
            return new User(id, name, email, age);
        }
    }
}

