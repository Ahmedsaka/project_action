package io.medalytics.projectactions.Models;

import io.medalytics.projectactions.Util.BaseModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
@Builder
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
