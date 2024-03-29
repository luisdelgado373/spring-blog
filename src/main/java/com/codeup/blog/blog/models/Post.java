package com.codeup.blog.blog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) UNSIGNED")
    private long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    private PostDetails postDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostImage> images;

    @ManyToMany(mappedBy = "posts")
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {
    }

    public Post(String title, String description, PostDetails postDetails, List<PostImage> images) {
        this.title = title;
        this.description = description;
        this.postDetails = postDetails;
        this.images = images;
    }

    public Post(String title, String description) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PostDetails getPostDetails() {
        return postDetails;
    }

    public void setPostDetails(PostDetails postDetails) {
        this.postDetails = postDetails;
    }

    public List<PostImage> getImages() {
        return images;
    }

    public void setImages(List<PostImage> images) {
        this.images = images;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}