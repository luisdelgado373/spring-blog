package com.codeup.blog.blog;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) UNSIGNED")
    private long id;

    @Column(length = 100, nullable = false, unique = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Post(){
    }

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Post(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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
}