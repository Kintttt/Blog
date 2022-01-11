package com.example.blog.controller;


import com.example.blog.model.Person;
import com.example.blog.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //Creating new Users
    @PostMapping("/signUp")
    public String signUp(@RequestBody Person person){
        if(personService.savePersons(person) == null){
            System.out.println(person);
            return "Email is already taken, please try another";
        }
        personService.savePersons(person);
        System.out.println(person);
        return "User created with details of: " + person.toString();
    }

    @PostMapping("/login")
    public String loginUser(HttpServletRequest request, @RequestBody Person person){
        if(!personService.login(person)){
            return "Unable to log user in. Please confirm email and password";
        }
        request.getSession();
        return "Login successful.. WELCOME, "+ person.getFirstName();
    }

    @RequestMapping("/logout")
    public HttpStatus LOGOUT(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null) return null;
        session.invalidate();
        return HttpStatus.OK;
    }
}
