package io.medalytics.projectactions.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class ActionApiPayload implements Serializable {
    private long project_id;
    private String description;
    private String note;
}
