/*
 * @ (#) Certificate.java    1.0    16/05/2024
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
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "certificates")
public class Certificate implements Serializable {
    @Id
    @Column(name = "certificate_id")
    private String id;
    private String name;
    private String organization;
    @Column(name = "issue_date")
    private LocalDate issueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
    public Certificate() {
    }

    public Certificate(String id, String name, String organization, LocalDate issueDate) {
        this.id = id;
        this.name = name;
        this.organization = organization;
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", organization='" + organization + '\'' +
                ", issueDate=" + issueDate +
                '}';
    }
}
