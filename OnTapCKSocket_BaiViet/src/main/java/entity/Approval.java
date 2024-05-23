/*
 * @ {#} Approval.java   1.0     22/05/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   22/05/2024
 * @version:    1.0
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Embeddable
public class Approval implements Serializable {
    @Column(name = "approval_date")
    private LocalDate approvalDate;
    @Enumerated
    @Column(name = "status")
    private Status status;
}
