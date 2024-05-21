/*
 * @ (#) Position.java    1.0    16/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package entity;/*
 * @description:
 * @author: Bao Thong
 * @date: 16/05/2024
 * @version: 1.0
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "positions")
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "position_id")
    private String id;
    private String name;
    private String description;
    private double salary;
    private int number;
    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
    private List<Candidate> candidates;
    public Position() {
    }

    public Position(int number, double salary, String description, String name, String id) {
        this.number = number;
        this.salary = salary;
        this.description = description;
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Position{" +
                "number=" + number +
                ", salary=" + salary +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
