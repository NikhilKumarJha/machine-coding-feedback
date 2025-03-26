package com.nikhil.factory;

import com.nikhil.Entity.Comment;
import com.nikhil.Entity.Notification;
import com.nikhil.Entity.Post;
import com.nikhil.Entity.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class DefaultSocialEntityFactory implements SocialEntityFactory {
    @Override
    public User createUser(String id, String name, String email, String password, String profilePicture, String bio, List<String> friends, List<Post> posts, List<Notification> notifications) {
        return new User(id, name, email, password, profilePicture, bio, friends, posts, notifications);
    }

    @Override
    public Post createPost(String id, String userId, String content, Timestamp timestamp, List<String> likes, List<Comment> comments) {
        return new Post(UUID.randomUUID().toString(), userId, content, timestamp, likes, comments);
    }

    @Override
    public Comment createComment(String userId, String postId, String content) {
        return new Comment(UUID.randomUUID().toString(), userId, postId, content, new Timestamp(System.currentTimeMillis()));
    }
}
