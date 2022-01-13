package com.example.blog.controller;


import com.example.blog.model.Person;
import com.example.blog.model.PostModel;
import com.example.blog.repository.PersonRepository;
import com.example.blog.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;
    private final PersonRepository personRepo;

    @Autowired
    public PersonController(PersonService personService, PersonRepository personRepo) {
        this.personService = personService;
        this.personRepo = personRepo;
    }

    //Creating new Users
    @PostMapping("/signUp")
    public String signUp(@RequestBody Person person){
        Person user = personService.savePersons(person);

        if(user == null){
            return "Email taken, please try another";
        }
        System.out.println(person);
        return "User created with details: "+ person.toString();
    }

    @PostMapping("/login")
    public String loginUser(HttpServletRequest request, @RequestBody Person person){
        if(!personService.login(person)){
            return "Unable to log user in. Please confirm email and password" + person.getUserId();
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

    @PostMapping("/favorites/{userId}/{postId}")
    public String favoritePost(@PathVariable Long postId, @PathVariable Long userId){
        return personService.favoritePosts(userId, postId);
    }

    @PostMapping("friend/{userId}/{friendId}")
    public String addFriend(@PathVariable Long friendId, @PathVariable Long userId){
        return personService.addFriends(userId, friendId);
    }

    @GetMapping("/favPosts/{userId}")
    public List<PostModel> showFavoritePostsByUser(@PathVariable Long userId){
        return personService.getFavoritePosts(userId);
    }

    @GetMapping("/friendsList/{userId}")
    public List<Person> showFriendsList(@PathVariable Long userId){
        return personService.showFriendsList(userId);
    }


}
