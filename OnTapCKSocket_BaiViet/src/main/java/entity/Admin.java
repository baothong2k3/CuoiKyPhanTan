/*
 * @ {#} Admin.java   1.0     22/05/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Entity
public class Admin extends User{
}
