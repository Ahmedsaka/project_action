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
@Table(name = "projects")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Projects extends BaseModel {
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "completed", nullable = true)
    private boolean completed;
}
