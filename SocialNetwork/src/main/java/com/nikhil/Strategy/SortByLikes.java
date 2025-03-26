package com.nikhil.Strategy;

import com.nikhil.Entity.Post;

import java.util.List;

public class SortByLikes implements SortingStrategy {
    @Override
    public void sort(List<Post> posts) {
        posts.sort((o1, o2) -> o2.getLikes().size() - o1.getLikes().size());
    }
}
