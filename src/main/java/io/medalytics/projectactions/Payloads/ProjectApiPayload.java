package io.medalytics.projectactions.Payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectApiPayload {
    private String name;
    private String description;
    private boolean completed;

}
