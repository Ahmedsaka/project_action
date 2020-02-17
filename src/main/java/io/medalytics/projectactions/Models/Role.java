package io.medalytics.projectactions.Models;

import io.medalytics.projectactions.Util.BaseModel;
import io.medalytics.projectactions.Util.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Role extends BaseModel {

    @Enumerated(EnumType.STRING)
    private RoleType type;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Users> users = new HashSet<>();
}