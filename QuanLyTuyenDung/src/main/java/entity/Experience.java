/*
 * @ (#) Experience.java    1.0    16/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package entity;/*
 * @description:
 * @author: Bao Thong
 * @date: 16/05/2024
 * @version: 1.0
 */

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "experiences")
public class Experience implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "company_name")
    private String companyName;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @EqualsAndHashCode.Exclude
    @Column(name = "from_date")
    private LocalDate fromDate;
    @EqualsAndHashCode.Exclude
    @Column(name = "to_date")
    private LocalDate toDate;
    @EqualsAndHashCode.Exclude
    private String description;
    public Experience() {
    }
    public Experience(String companyName, LocalDate fromDate, LocalDate toDate, String description) {
        this.companyName = companyName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "companyName='" + companyName + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", description='" + description + '\'' +
                ", candidate=" + candidate +
                ", position=" + position +
                '}';
    }
}
