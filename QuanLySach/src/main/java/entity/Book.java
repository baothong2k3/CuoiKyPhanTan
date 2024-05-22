/*
 * @ (#) Book.java    1.0    22/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package entity;/*
 * @description:
 * @author: Bao Thong
 * @date: 22/05/2024
 * @version: 1.0
 */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "books")
@Inheritance(strategy = InheritanceType.JOINED)
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected String ISBN;
    protected String name;
    @Column(name = "publish_year")
    protected int pushlishYear;
    @Column(name = "num_of_pages")
    protected int numOfPages;
    protected double price;

    @ElementCollection
    @CollectionTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
    @Column(name = "author", nullable = false)
    protected Set<String> books_authors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    protected Publisher publisher;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    protected Set<Reviews> reviews;
    public Book() {
    }

    public Book(String ISBN, String name, int pushlishYear, int numOfPages, double price, Set<String> books_authors, Publisher publisher) {
        this.ISBN = ISBN;
        this.name = name;
        this.pushlishYear = pushlishYear;
        this.numOfPages = numOfPages;
        this.price = price;
        this.books_authors = books_authors;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", pushlishYear=" + pushlishYear +
                ", numberOfPages=" + numOfPages +
                ", price=" + price +
                ", author=" + books_authors +
                ", publisher=" + publisher +
                '}';
    }
}
