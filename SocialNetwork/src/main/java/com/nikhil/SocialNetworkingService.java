package com.nikhil;

import com.nikhil.Entity.*;
import com.nikhil.Strategy.SortByTimestamp;
import com.nikhil.Strategy.SortingContext;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SocialNetworkingService {

    private Map<String, User> users;
    private Map<String, Post> posts;
    private SortingContext sortingContext;

    public SocialNetworkingService() {
        users = new ConcurrentHashMap<>();
        posts = new ConcurrentHashMap<>();
        sortingContext = new SortingContext();
    }

    private static class SocialNetworkingServiceSingletonHelper {
        private static final SocialNetworkingService instance = new SocialNetworkingService();
    }

    public static SocialNetworkingService getInstance() {
        return SocialNetworkingServiceSingletonHelper.instance;
    }

    public void registerUser(User user) {
        users.put(user.getId(), user);
    }

    public User loginUser(String email, String password) {
        for (User user : users.values()) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void UpdateUserProfile(User user) {
        users.put(user.getId(), user);
    }

    public void sendFriendRequest(String senderId, String receiverId) {
        User receiver = users.get(receiverId);
        if (receiver != null) {
            Notification notification = new Notification(
                    UUID.randomUUID().toString(),
                    receiverId,
                    NotificationType.FRIEND_REQUEST,
                    "Friend request from " + senderId,
                    new Timestamp(System.currentTimeMillis())
            );
            addNotification(receiverId, notification);
        }
    }

    public void acceptFriendRequest(String userId, String friendId) {
        User user = users.get(userId);
        User friend = users.get(friendId);
        if (user != null && friend != null) {
            user.getFriends().add(friendId);
            friend.getFriends().add(userId);
            Notification notification = new Notification(
                    UUID.randomUUID().toString(),
                    friendId,
                    NotificationType.FRIEND_REQUEST_ACCEPTED,
                    "Friend request accepted by " + userId,
                    new Timestamp(System.currentTimeMillis())
            );
            addNotification(friendId, notification);
        }
    }

    public void createPost(Post post) {
        posts.putIfAbsent(post.getId(), post);
        User user = users.get(post.getUserId());
        if (user != null) {
            user.getPosts().add(post);
        }
    }

    public List<Post> getNewsFeed(String userId) {
        List<Post> userFeed = new ArrayList<>();
        User user = users.get(userId);
        if (user != null) {
            List<String> friends = user.getFriends();
            if (friends != null && !friends.isEmpty()) {
                for (String friendId : friends) {
                    User friendInfo = users.get(friendId);
                    userFeed.addAll(friendInfo.getPosts());
                }
            }
            userFeed.addAll(user.getPosts());
            sortingContext.setSortingStrategy(new SortByTimestamp());
            sortingContext.sort(userFeed);
        }
        return userFeed;
    }

    public void likePost(String userId, String postId) {
        Post post = posts.get(postId);
        if (post != null && !post.getLikes().contains(userId)) {
            post.getLikes().add(userId);
            Notification notification = new Notification(
                    UUID.randomUUID().toString(),
                    post.getUserId(),
                    NotificationType.LIKE,
                    "Post liked by " + userId,
                    new Timestamp(System.currentTimeMillis())
            );
            addNotification(post.getUserId(), notification);
        }
    }

    public void commentOnPost(Comment comment) {
        Post post = posts.get(comment.getPostId());
        if (post != null) {
            post.getComments().add(comment);
            Notification notification = new Notification(
                    UUID.randomUUID().toString(),
                    post.getUserId(),
                    NotificationType.COMMENT,
                    comment.getUserId() + " commented on post",
                    new Timestamp(System.currentTimeMillis())
            );
            addNotification(post.getUserId(), notification);
        }
    }

    private void addNotification(String userId, Notification notification) {
        User user = users.get(userId);
        List<Notification> notifications = user.getNotifications();
        if (notifications == null) {
            notifications = new ArrayList<>();
        }
        notifications.add(notification);
        user.setNotifications(notifications);
    }

    public List<Notification> getNotifications(String userId) {
        return users.get(userId).getNotifications();
    }

}
