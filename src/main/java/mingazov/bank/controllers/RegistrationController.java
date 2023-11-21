package mingazov.bank.controllers;

import lombok.RequiredArgsConstructor;
import mingazov.bank.dto.CustomerAuthenticateRequestDTO;
import mingazov.bank.entities.Customer;
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
    public ResponseEntity<String> register(@RequestBody CustomerAuthenticateRequestDTO customerDTO){
        var customer = new Customer();
        customer.setUsername(customerDTO.getUsername());
        customer.setPin(customerDTO.getPin());
        if(registrationServiceImpl.createCustomerIfUsernameNotExist(customer))
           return ResponseEntity.ok("Пользователь " + customer.getUsername() + " успешно зарегистрирован");
       return ResponseEntity.status(HttpStatus.CONFLICT).body("Пользователь с такими именем уже существует");
    }
    // todo дописать с дто


}

