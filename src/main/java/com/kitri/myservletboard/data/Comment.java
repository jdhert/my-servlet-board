package com.kitri.myservletboard.data;

import java.time.LocalDateTime;

public class Comment {
    private long id;
    private String content;
    private LocalDateTime createdAt;
    private long member_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Comment() {
    }

    public Comment(long id, String content, LocalDateTime createdAt, long member_id, String name, long board_id) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.member_id = member_id;
        this.name = name;
        this.board_id = board_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(long board_id) {
        this.board_id = board_id;
    }

    private long board_id;
}
