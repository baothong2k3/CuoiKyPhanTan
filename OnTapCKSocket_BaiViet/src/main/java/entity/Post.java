/*
 * @ {#} Post.java   1.0     22/05/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   22/05/2024
 * @version:    1.0
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "posts")
public class Post implements Serializable {
    @Id
    @Column(name = "post_id")
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "contents")
    private String contents;
    @Column(name = "views")
    private int views;
    @Column(name = "likes")
    private int likes;
    @Column(name = "shares")
    private int shares;

    @Embedded
    private Approval approval;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;
    @ManyToOne
    @JoinColumn(name = "admin_name")
    private Admin admin;
}
