package io.medalytics.projectactions.Payloads;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProjectApiPayload implements Serializable {
    private String name;
    private String description;
    private boolean completed;

}
