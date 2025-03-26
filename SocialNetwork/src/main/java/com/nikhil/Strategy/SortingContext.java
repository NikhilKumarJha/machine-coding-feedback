package com.nikhil.Strategy;

import com.nikhil.Entity.Post;

import java.util.List;

public class SortingContext {
    private SortingStrategy sortingStrategy;

    public SortingContext() {}

    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void sort(List<Post> posts){
        if(sortingStrategy!=null){
            sortingStrategy.sort(posts);
        }
    }
}
