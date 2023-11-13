package com.vti.shopeebe.controller;

import com.vti.shopeebe.modal.entity.Account;
import com.vti.shopeebe.modal.request.CreateAccountRequest;
import com.vti.shopeebe.modal.request.UpdateAccountRequest;
import com.vti.shopeebe.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/account")
@CrossOrigin("*")
@Validated
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("/get-all")
    public List<Account> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
            Account account = service.getById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(account);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateAccountRequest request){
           service.create(request);
           return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody UpdateAccountRequest request,@PathVariable int id){
        service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
