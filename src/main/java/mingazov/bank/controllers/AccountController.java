package mingazov.bank.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mingazov.bank.dto.CustomerAddAccountRequestDTO;
import mingazov.bank.services.interfaces.AccountService;
import mingazov.bank.services.interfaces.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Account API")
public class AccountController {
    private final CustomerService customerService;
    private final AccountService accountService;
    @Operation(summary = "Добавляет счета клиенту")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успех"),
            @ApiResponse(responseCode = "404", description = "Клиент не был найден"),
            @ApiResponse(responseCode = "403", description = "Неверно введен пин-код")
    })
    @PostMapping("/addAccount")
    public ResponseEntity<String> addAccount(@RequestBody CustomerAddAccountRequestDTO accountRequestDTO) {
        var customer = customerService.checkUsernameAndPin(accountRequestDTO);
        accountService.addAccountsForCustomer(customer, accountRequestDTO);
        // todo сделать через формат и проценты?
        return ResponseEntity.ok("У пользователя " + accountRequestDTO.getUsername()
                + " успешно создано " + accountRequestDTO.getAmountOfAccounts() + " счетов" );

    }
}
