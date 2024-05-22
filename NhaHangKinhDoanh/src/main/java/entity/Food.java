/*
 * @ (#) Food.java    1.0    21/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package entity;/*
 * @description:
 * @author: Bao Thong
 * @date: 21/05/2024
 * @version: 1.0
 */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "foods")
public class Food extends Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "preparation_time")
    private int preparationTime;
    @Column(name = "serving_time")
    private int servingTime;
    public Food() {
    }

    public Food(String id, String name, double price, String description, boolean onSepecial, Type type, int preparationTime, int servingTime) {
        super(id, name, price, description, onSepecial);
        this.type = type;
        this.preparationTime = preparationTime;
        this.servingTime = servingTime;
    }

    @Override
    public String toString() {
        return super.toString() + "Food{" +
                "type=" + type +
                ", preparationTime=" + preparationTime +
                ", servingTime=" + servingTime +
                '}';
    }
}
