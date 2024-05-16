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
import lombok.Getter;
import lombok.Setter;
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
    public Position(String id, String name, String description, double salary, int number) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.salary = salary;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", number=" + number +
                ", candidates=" + candidates +
                '}';
    }
}
