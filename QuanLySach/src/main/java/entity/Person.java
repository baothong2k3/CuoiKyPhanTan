/*
 * @ (#) Person.java    1.0    22/05/2024
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
@Table(name = "people")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    private String email;
    @Column(name = "professional_title")
    private String professionalTitle;
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<Reviews> reviews;
    public Person() {
    }

    public Person(String lastName, String firstName, String email, String professionalTitle) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.professionalTitle = professionalTitle;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", personalTitle='" + professionalTitle + '\'' +
                '}';
    }
}
