package mingazov.bank.controllers;

import lombok.RequiredArgsConstructor;
import mingazov.bank.dto.CustomerCreateRequestDTO;
import mingazov.bank.services.interfaces.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationServiceImpl;

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody CustomerCreateRequestDTO customerDTO){
        var customer = CustomerCreateRequestDTO.convert(customerDTO);
        if(registrationServiceImpl.createCustomerIfUsernameNotExist(customer))
           return ResponseEntity.ok("Пользователь " + customer.getUsername() + " успешно зарегистрирован");
       return ResponseEntity.status(HttpStatus.CONFLICT).body("Пользователь с такими именем уже существует");
    }
    // todo дописать с дто


}

