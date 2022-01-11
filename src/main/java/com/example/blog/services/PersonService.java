package com.example.blog.services;


import com.example.blog.model.Person;
import com.example.blog.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PersonService {

    private final PersonRepository personRepo;

    @Autowired
    public PersonService(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    public Person savePersons(Person person){

        if(person.getEmail() != null){
            return null;
        }
       personRepo.save(person);
       return person;
    }

    public boolean login(Person person){

        if(personRepo.findPersonByEmail(person.getEmail()) == null){
            return false;
        }
        return Objects.equals(personRepo.findPersonByEmail(person.getEmail()).getPassword(), person.getPassword());
    }
}
