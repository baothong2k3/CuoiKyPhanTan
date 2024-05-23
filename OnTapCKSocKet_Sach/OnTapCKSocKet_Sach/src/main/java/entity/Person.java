/*
 * @ {#} Person.java   1.0     03/05/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@ToString
@Entity
@Table(name = "people")
public class Person implements Serializable {
    @Id
    @Column(name = "person_id")
    private String id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "email")
    private String email;
    @Column(name = "professional_title")
    private String professionalTitle;

    public Person(String id, String lastName, String firstName, String email, String professionalTitle) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.professionalTitle = professionalTitle;
    }
}
