/*
 * @ (#) beverages.java    1.0    21/05/2024
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
@Table(name = "beverages")
public class Beverage extends Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Enumerated(EnumType.STRING)
    private Size size;
    @Column(name = "supplier_name")
    private String supplierName;
    public Beverage() {
    }
    public  Beverage(String id, String name, double price, String description, boolean onSepecial, Size size, String supplierName){
        super(id, name, price, description, onSepecial);
        this.size = size;
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return super.toString() + "Beverage{" +
                "size=" + size +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }
}
