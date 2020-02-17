package io.medalytics.projectactions.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class AuthenticationLoginRequestPayload implements Serializable {
    private String username;
    private String password;
}
