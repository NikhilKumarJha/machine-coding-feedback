package com.nikhil.Entity;

import java.sql.Timestamp;

public class Notification {
    private String id;
    private String userId;
    private NotificationType notificationType;
    private String content;
    private Timestamp timestamp;

    public Notification(String id, String userId, NotificationType notificationType, String content, Timestamp timestamp) {
        this.id = id;
        this.userId = userId;
        this.notificationType = notificationType;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
