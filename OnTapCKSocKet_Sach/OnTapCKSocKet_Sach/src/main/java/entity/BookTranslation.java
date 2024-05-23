/*
 * @ {#} BookTranslation.java   1.0     03/05/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "book_translations")
public class BookTranslation extends Book implements Serializable {
    @Column(name = "translate_name")
    private  String translateName;
    @Column(name = "language")
    private String language;

    public BookTranslation(String ISBN, String name, int publishYear, int numberOfPage, double price, Set<String> authors, Publisher publisher, String translateName, String language) {
        super(ISBN, name, publishYear, numberOfPage, price, authors, publisher);
        this.translateName = translateName;
        this.language = language;
    }

    @Override
    public String toString() {
       return super.toString() + "BookTranslation{" +
               "translateName='" + translateName + '\'' +
               ", language='" + language + '\'' +
               '}';
    }
}
