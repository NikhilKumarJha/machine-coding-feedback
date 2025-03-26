package com.nikhil.Strategy;

import com.nikhil.Entity.Post;

import java.util.List;

public class SortByTimestamp implements SortingStrategy {
    @Override
    public void sort(List<Post> posts) {
        posts.sort((o1, o2) -> o1.getTimestamp().compareTo(o2.getTimestamp()));
    }
}
