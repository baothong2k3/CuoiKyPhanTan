/*
 * @ (#) Item.java    1.0    21/05/2024
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
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected String id;
    protected String name;
    protected double price;
    protected String description;
    @Column(name = "on_special")
    protected boolean onSepecial;

    @ManyToMany(mappedBy = "items")
    protected Set<Ingredient> ingredients;
    public Item() {
    }

    public Item(String id, String name, double price, String description, boolean onSepecial) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.onSepecial = onSepecial;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", onSepecial=" + onSepecial +
                '}';
    }
}
