package io.medalytics.projectactions.Models;

import io.medalytics.projectactions.Util.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users extends BaseModel {
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
}
