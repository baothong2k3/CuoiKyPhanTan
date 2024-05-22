/*
 * @ (#) BookTranslation.java    1.0    22/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package entity;/*
 * @description:
 * @author: Bao Thong
 * @date: 22/05/2024
 * @version: 1.0
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "book_translations")
public class BookTranslation extends Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "translate_name")
    private String translationName;
    private String language;

    public BookTranslation() {
    }

    public BookTranslation(String ISBN, String name, int pushlishYear, int numberOfPages, double price, Set<String> author, Publisher publisher, String translationName, String language) {
        super(ISBN, name, pushlishYear, numberOfPages, price, author, publisher);
        this.translationName = translationName;
        this.language = language;
    }

    @Override
    public String toString() {
        return super.toString() + "BookTranslation{" +
                "translationName='" + translationName + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
