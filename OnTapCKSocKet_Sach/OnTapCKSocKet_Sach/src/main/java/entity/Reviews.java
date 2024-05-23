/*
 * @ {#} Reviews.java   1.0     03/05/2024
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
 * @date:   03/05/2024
 * @version:    1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Reviews implements Serializable {
    @Column(name = "rating")
    private int rating;
    @Column(name = "comment")
    private String comment;

    @ToString.Exclude
    @Id
    @ManyToOne
    @JoinColumn(name = "ISBN")
    private Book book;
    @ToString.Exclude
    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

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
                '}';
    }
}
