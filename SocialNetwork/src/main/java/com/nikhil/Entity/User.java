package com.nikhil.Entity;

import com.nikhil.observer.Observer;

import java.util.*;

public class User implements Observer {
    private String id;
    private String name;
    private String email;
    private String password;
    private String profilePicture;
    private String bio;
    private List<String> friends;
    private List<Post> posts;
    private List<Notification> notifications;

    public User(String id, String name, String email, String password, String profilePicture, String bio, List<String> friends, List<Post> posts, List<Notification> notifications) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.friends = friends;
        this.posts = posts;
        this.notifications = notifications;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public void update(Notification notification) {
        System.out.println("Type: " + notification.getNotificationType());
        System.out.println("Content: " + notification.getContent());
        System.out.println();
    }
}
