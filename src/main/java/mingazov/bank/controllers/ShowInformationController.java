package mingazov.bank.controllers;

import lombok.RequiredArgsConstructor;
import mingazov.bank.dto.CustomerAndAccountsAndBalanceResponseDTO;
import mingazov.bank.dto.CustomerAuthenticateRequestDTO;
import mingazov.bank.dto.TransactionsResponseDTO;
import mingazov.bank.services.interfaces.AccountService;
import mingazov.bank.services.interfaces.CustomerService;
import mingazov.bank.services.interfaces.LogBalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShowInformationController {
    private final AccountService accountService;
    private final CustomerService customerService;
    private final LogBalanceService logBalanceService;
    @PostMapping("/showAccounts")
    //todo Как вернуть по разному параметризованные респонс энетити? И как это вынести?
    public ResponseEntity<CustomerAndAccountsAndBalanceResponseDTO> showAccounts(@RequestBody CustomerAuthenticateRequestDTO auth) {
        var customer = customerService.findCustomerByUsername(auth.getUsername()).orElse(null);
        if (customer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        if (!customerService.isValidPin(auth.getPin(), customer))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        var accounts = accountService.findAccountsByCustomer_Id(customer.getId());
        var responseDTO = new CustomerAndAccountsAndBalanceResponseDTO(customer, accounts);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/showTransactions")
    //todo Как вернуть по разному параметризованные респонс энетити? И как это вынести?
    public ResponseEntity<TransactionsResponseDTO> showLogs(@RequestBody CustomerAuthenticateRequestDTO auth) {
        var customer = customerService.findCustomerByUsername(auth.getUsername()).orElse(null);
        if (customer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        if (!customerService.isValidPin(auth.getPin(), customer))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        var logs = logBalanceService.findAccountsByCustomer_Id(customer.getId());
        var responseDTO = new TransactionsResponseDTO(customer, logs);
        return ResponseEntity.ok().body(responseDTO);
    }
}
