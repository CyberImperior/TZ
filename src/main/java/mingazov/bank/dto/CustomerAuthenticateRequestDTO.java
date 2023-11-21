package mingazov.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAuthenticateRequestDTO {
    private String username;
    private Short pin;
}
