package com.nikhil.factory;

import com.nikhil.Entity.Comment;
import com.nikhil.Entity.Notification;
import com.nikhil.Entity.Post;
import com.nikhil.Entity.User;

import java.sql.Timestamp;
import java.util.List;

public interface SocialEntityFactory {
    User createUser(String id,String name, String email, String password, String profilePicture, String bio, List<String> friends, List<Post> posts, List<Notification> notifications);
    Post createPost(String id, String userId, String content, Timestamp timestamp, List<String> likes, List<Comment> comments);
    Comment createComment(String userId, String postId, String content);
}
