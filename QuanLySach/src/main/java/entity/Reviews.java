/*
 * @ (#) Reviews.java    1.0    22/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package entity;/*
 * @description:
 * @author: Bao Thong
 * @date: 22/05/2024
 * @version: 1.0
 */

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "reviews")
public class Reviews implements Serializable {
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Exclude
    private int rating;
    @EqualsAndHashCode.Exclude
    private String comment;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ISBN")
    private Book book;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    public Reviews() {
    }

    public Reviews(int rating, String comment, Book book, Person person) {
        this.rating = rating;
        this.comment = comment;
        this.book = book;
        this.person = person;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "rating=" + rating +
                ", comment='" + comment + '\'' +
                ", book=" + book +
                ", person=" + person +
                '}';
    }

}
