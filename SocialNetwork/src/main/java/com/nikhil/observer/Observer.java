package com.nikhil.observer;

import com.nikhil.Entity.Notification;

public interface Observer {
    void update(Notification notification);
}
