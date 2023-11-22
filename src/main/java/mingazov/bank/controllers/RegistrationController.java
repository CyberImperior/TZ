package mingazov.bank.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mingazov.bank.dto.CustomerAuthenticateRequestDTO;
import mingazov.bank.entities.Customer;
import mingazov.bank.services.interfaces.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Registration API")
public class RegistrationController {
    private final RegistrationService registrationServiceImpl;
    @Operation(summary = "Регистрирует клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успех"),
            @ApiResponse(responseCode = "409", description = "Клиент с таким именем уже существует")
    })
    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody CustomerAuthenticateRequestDTO customerDTO){
        var customer = new Customer();
        customer.setUsername(customerDTO.getUsername());
        customer.setPin(customerDTO.getPin());
        registrationServiceImpl.createCustomerIfUsernameNotExist(customer);
        return ResponseEntity.ok("Пользователь " + customer.getUsername() + " успешно зарегистрирован");
    }
}

