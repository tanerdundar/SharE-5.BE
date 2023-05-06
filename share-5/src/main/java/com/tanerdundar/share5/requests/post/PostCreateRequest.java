package com.tanerdundar.share5.requests.post;

import com.tanerdundar.share5.dao.PostRepository;
import com.tanerdundar.share5.dao.UserRepository;
import com.tanerdundar.share5.entities.Post;
import com.tanerdundar.share5.entities.Statu;
import com.tanerdundar.share5.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Data
public class PostCreateRequest {



    private String content;

    public Post createOnePost() {
        Post newPost = new Post();
        newPost.setContent(this.content);
    return newPost;
    }
}
