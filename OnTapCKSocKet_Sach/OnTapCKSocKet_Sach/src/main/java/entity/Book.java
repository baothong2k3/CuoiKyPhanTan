/*
 * @ {#} Book.java   1.0     03/05/2024
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
import java.util.Set;

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
@Table(name = "books")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Book.listRatedBooks",
                query = "SELECT b FROM Book b JOIN b.reviews r WHERE :author MEMBER OF b.authors AND r.rating >= :rating"),
        @NamedQuery(name = "Book.countBooksByAuthor",
                query = "SELECT bt.authors, COUNT(bt) FROM BookTranslation bt JOIN bt.authors a GROUP BY a"),
})
public class Book implements Serializable {
    @Id
    @Column(name = "ISBN")
    protected String ISBN;
    @Column(name = "name")
    protected String name;
    @Column(name = "publish_year")
    protected int publishYear;
    @Column(name = "num_of_pages")
    protected int numOfPages;
    @Column(name = "price")
    protected double price;
    @ToString.Exclude
    @ElementCollection
    @CollectionTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
    @Column(name = "author", nullable = false)
    protected Set<String> authors;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    protected Publisher publisher;
    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    protected Set<Reviews> reviews;

    public Book(String ISBN, String name, int publishYear, int numOfPages, double price, Set<String> authors, Publisher publisher) {
        this.ISBN = ISBN;
        this.name = name;
        this.publishYear = publishYear;
        this.numOfPages = numOfPages;
        this.price = price;
        this.authors = authors;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", publishYear=" + publishYear +
                ", numOfPages=" + numOfPages +
                ", price=" + price +
                '}';
    }
}
