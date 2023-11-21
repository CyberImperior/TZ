package mingazov.bank.controllers;

import lombok.RequiredArgsConstructor;
import mingazov.bank.dto.TransferRequestDTO;
import mingazov.bank.entities.OperationType;
import mingazov.bank.services.implementations.CustomerServiceImpl;
import mingazov.bank.services.implementations.OperationsWithMoneyServiceImpl;
import mingazov.bank.services.interfaces.CustomerService;
import mingazov.bank.services.interfaces.OperationsWithMoneyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operation")
public class OperationWithMoneyController {
    // todo имплементить
    private final OperationsWithMoneyService operationsWithMoneyService;
    private final CustomerService customerService;
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequestDTO requestDTO){
        // todo можно сделать нормально обработку нула
        // этот код универсальный можно в другой класс
        var customer = customerService.findCustomerByUsername(requestDTO.getUsername()).orElse(null);
        if (customer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь с именем "
                    + requestDTO.getUsername() + " не найден");
        if (!customerService.isValidPin(requestDTO.getPin(), customer))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Неверно введен пин-код");
        operationsWithMoneyService.operation(requestDTO.getAccountNumber(),
                requestDTO.getAccountNumberTo(),
                requestDTO.getAmountOfOperation(),
                customer,
                OperationType.TRANSFER);
        return ResponseEntity.ok().body("Вы перевели!");
    }
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody TransferRequestDTO requestDTO){
        // todo можно сделать нормально обработку нула
        // этот код универсальный можно в другой класс
        var customer = customerService.findCustomerByUsername(requestDTO.getUsername()).orElse(null);
        if (customer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь с именем "
                    + requestDTO.getUsername() + " не найден");
        if (!customerService.isValidPin(requestDTO.getPin(), customer))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Неверно введен пин-код");
        operationsWithMoneyService.operation(requestDTO.getAccountNumber(),
                requestDTO.getAccountNumberTo(),
                requestDTO.getAmountOfOperation(),
                customer,
                OperationType.WITHDRAW);
        return ResponseEntity.ok().body("Вы сняли!");
    }
    @PostMapping("/replenishment")
    public ResponseEntity<String> replenishment(@RequestBody TransferRequestDTO requestDTO){
        // todo можно сделать нормально обработку нула
        // этот код универсальный можно в другой класс
        var customer = customerService.findCustomerByUsername(requestDTO.getUsername()).orElse(null);
        if (customer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь с именем "
                    + requestDTO.getUsername() + " не найден");
        if (!customerService.isValidPin(requestDTO.getPin(), customer))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Неверно введен пин-код");
        operationsWithMoneyService.operation(requestDTO.getAccountNumber(),
                requestDTO.getAccountNumberTo(),
                requestDTO.getAmountOfOperation(),
                customer,
                OperationType.REPLENISHMENT);
        return ResponseEntity.ok().body("Вы пополнили!");
    }
}
