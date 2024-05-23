/*
 * @ {#} Comment.java   1.0     22/05/2024
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
import java.time.LocalDateTime;

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
@Table(name = "comments")
public class Comment implements Serializable {
    @Column(name = "contents")
    private String contents;
    @Id
    @Column(name = "comment_date")
    private LocalDateTime commentDate;
    @Column(name = "likes")
    private int likes;

    @ToString.Exclude
    @Id
    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;
    @ToString.Exclude
    @Id
    @JoinColumn(name = "post_id")
    @ManyToOne
    private Post post;
}
