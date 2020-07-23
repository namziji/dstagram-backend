package com.candoit.dstagram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int boardId;

    @Column
    private String description;

    @Column
    private String imageUrl;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {createdAt = LocalDateTime.now();}

    @PrePersist
    protected void onUpdate() {updatedAt = LocalDateTime.now();}

    public Post(int boardId, String description, String imageUrl, LocalDateTime createAt, LocalDateTime updatedAt) {
        this.boardId = boardId;
        this.description = description;
        this.imageUrl = imageUrl;
        this.createdAt = createAt;
        this.updatedAt = updatedAt;

    }

}
