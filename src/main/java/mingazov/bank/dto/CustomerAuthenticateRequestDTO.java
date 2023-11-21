package mingazov.bank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAuthenticateRequestDTO {
    @Schema(title = "Логин пользователя", example = "Fedor")
    private String username;
    @Schema(title = "Пин-код", example = "9857")
    private Short pin;
}
