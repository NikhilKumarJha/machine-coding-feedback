package com.nikhil.Strategy;

import com.nikhil.Entity.Post;

import java.util.List;

public interface SortingStrategy {
    void sort(List<Post> posts);
}
