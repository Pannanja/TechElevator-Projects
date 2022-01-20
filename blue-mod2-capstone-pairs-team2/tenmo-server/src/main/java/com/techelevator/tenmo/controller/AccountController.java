package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.LoginDTO;
import com.techelevator.tenmo.model.RegisterUserDTO;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
public class AccountController {

    //private final TokenProvider tokenProvider;
    //private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private int accountId;
    private AccountDao accountDao;

    public AccountController(AccountDao accountDao, int accountId) {
        this.accountDao = accountDao;
        this.accountId = accountId;
    }

    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public BigDecimal getBalance() {
        return accountDao.getBalance(accountId);
    }

    //sendTransfer

    //listTransfers

    //getTransferDetails
}
