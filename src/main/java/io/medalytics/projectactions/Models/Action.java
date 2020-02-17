package io.medalytics.projectactions.Models;

import io.medalytics.projectactions.Util.BaseModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "actions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Action extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects project;
    @Column(name = "description")
    private String description;
    @Column(name = "note")
    private String note;
}
