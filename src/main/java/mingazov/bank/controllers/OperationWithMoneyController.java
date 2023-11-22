package mingazov.bank.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mingazov.bank.dto.TransferRequestDTO;
import mingazov.bank.entities.OperationType;
import mingazov.bank.services.interfaces.CustomerService;
import mingazov.bank.services.interfaces.OperationsWithMoneyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operation")
@Tag(name = "Money transactions  API")
public class OperationWithMoneyController {
    private final OperationsWithMoneyService operationsWithMoneyService;
    private final CustomerService customerService;
    @Operation(summary = "Перевод")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успех"),
            @ApiResponse(responseCode = "402", description = "Недостаточно средств"),
            @ApiResponse(responseCode = "403", description = "Неверно введен пин-код"),
            @ApiResponse(responseCode = "404", description = "Клиент не был найден или неверно введен номер счета")
    })
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequestDTO requestDTO){
        var customer = customerService.checkUsernameAndPin(requestDTO);
        operationsWithMoneyService.operation(requestDTO.getAccountNumber(), requestDTO.getAccountNumberTo(), requestDTO.getAmountOfOperation(), customer, OperationType.TRANSFER);
        return ResponseEntity.ok().body("Перевод прошел успешно!");

    }
    @Operation(summary = "Снятие средств")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успех"),
            @ApiResponse(responseCode = "402", description = "Недостаточно средств"),
            @ApiResponse(responseCode = "403", description = "Неверно введен пин-код"),
            @ApiResponse(responseCode = "404", description = "Клиент не был найден или неверно введен номер счета")
    })
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody TransferRequestDTO requestDTO){
        var customer = customerService.checkUsernameAndPin(requestDTO);
        operationsWithMoneyService.operation(requestDTO.getAccountNumber(), requestDTO.getAccountNumberTo(), requestDTO.getAmountOfOperation(), customer, OperationType.WITHDRAW);
        return ResponseEntity.ok().body("Вы успешно сняли средства");
    }
    @Operation(summary = "Пополнение счета")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успех"),
            @ApiResponse(responseCode = "403", description = "Неверно введен пин-код"),
            @ApiResponse(responseCode = "404", description = "Клиент не был найден или неверно введен номер счета")
    })
    @PostMapping("/replenishment")
    public ResponseEntity<String> replenishment(@RequestBody TransferRequestDTO requestDTO){
        var customer = customerService.checkUsernameAndPin(requestDTO);
        operationsWithMoneyService.operation(requestDTO.getAccountNumber(), requestDTO.getAccountNumberTo(), requestDTO.getAmountOfOperation(), customer, OperationType.REPLENISHMENT);
        return ResponseEntity.ok().body("Успешное пополнение");
    }
}
