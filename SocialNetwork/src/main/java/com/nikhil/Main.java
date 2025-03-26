package com.nikhil;

import com.nikhil.Entity.Comment;
import com.nikhil.Entity.Notification;
import com.nikhil.Entity.Post;
import com.nikhil.Entity.User;
import com.nikhil.factory.DefaultSocialEntityFactory;
import com.nikhil.factory.SocialEntityFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SocialNetworkingService socialNetworkingService = SocialNetworkingService.getInstance();
        SocialEntityFactory socialEntityFactory = new DefaultSocialEntityFactory();
        User user1 = socialEntityFactory.createUser("1", "John Doe", "john@example.com", "password", "profile1.jpg", "I love coding!", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        User user2 = socialEntityFactory.createUser("2", "Jane Smith", "jane@example.com", "password", "profile2.jpg", "Exploring the world!", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        socialNetworkingService.registerUser(user1);
        socialNetworkingService.registerUser(user2);

        User loggedInUser = socialNetworkingService.loginUser("john@example.com", "password");
        if (loggedInUser != null) {
            System.out.println("User logged in: " + loggedInUser.getName());
        } else {
            System.out.println("Invalid email or password.");
        }

        socialNetworkingService.sendFriendRequest(user1.getId(), user2.getId());

        socialNetworkingService.acceptFriendRequest(user2.getId(), user1.getId());

        Post post1 = socialEntityFactory.createPost("post1", user1.getId(), "My first post!", new Timestamp(System.currentTimeMillis()), new ArrayList<>(), new ArrayList<>());
        Post post2 = new Post("post2", user2.getId(), "Having a great day!", new Timestamp(System.currentTimeMillis()), new ArrayList<>(), new ArrayList<>());
        socialNetworkingService.createPost(post1);
        socialNetworkingService.createPost(post2);

        socialNetworkingService.likePost(user2.getId(), post1.getId());

        Comment comment = new Comment("comment1", user2.getId(), post1.getId(), "Great post!", new Timestamp(System.currentTimeMillis()));
        socialNetworkingService.commentOnPost(comment);

        // Get newsfeed
        List<Post> newsfeed = socialNetworkingService.getNewsFeed(user1.getId());
        System.out.println("Newsfeed:");
        for (Post post : newsfeed) {
            System.out.println("Post: " + post.getContent());
            System.out.println("Likes: " + post.getLikes().size());
            System.out.println("Comments: " + post.getComments().size());
            System.out.println();
        }

        // Get notifications
        List<Notification> notifications = socialNetworkingService.getNotifications(user1.getId());
        System.out.println("Notifications:");
        for (Notification notification : notifications) {
            System.out.println("Type: " + notification.getNotificationType());
            System.out.println("Content: " + notification.getContent());
            System.out.println();
        }

    }
}