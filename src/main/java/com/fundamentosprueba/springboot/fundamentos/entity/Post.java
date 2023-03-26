package com.fundamentosprueba.springboot.fundamentos.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false, unique = true)
    private long id;
    @Column(name = "description", length = 255)
    private String descripion;
    @ManyToOne
    private User user;

    public Post() {
    }

    public Post(String descripion, User user) {
        this.descripion = descripion;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", descripion='" + descripion + '\'' +
                ", user=" + user +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
