package com.learn.notes.api;

import com.learn.notes.model.Account;
import com.learn.notes.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private AccountRepository repository;

    @GetMapping("/")
    private List<Account> getAllDemos(){
        return  repository.findAll();
    }

    @PostMapping("/")
    private Account addDemo(@RequestBody Account account){
         return repository.save(account);
    }

}
