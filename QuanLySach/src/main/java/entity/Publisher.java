/*
 * @ (#) Publisher.java    1.0    22/05/2024
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
@Table(name = "publishers")
public class Publisher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "publisher_id")
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private Set<Book> books;
    public Publisher() {
    }

    public Publisher(String id, String name, String address, String email, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
