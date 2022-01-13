package com.example.blog.services;


import com.example.blog.model.Person;
import com.example.blog.model.PostModel;
import com.example.blog.repository.PersonRepository;
import com.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

    private final PersonRepository personRepo;
    private final PostRepository postRepo;

    @Autowired
    public PersonService(PersonRepository personRepo, PostRepository postRepo) {
        this.personRepo = personRepo;
        this.postRepo = postRepo;
    }

    public Person savePersons(Person person){

        Person person1 = personRepo.findPersonByEmail(person.getEmail());

        if(person1 != null){
            return null;
        }
       return personRepo.save(person);
    }


    public boolean login(Person person){

        if(personRepo.findPersonByEmail(person.getEmail()) == null){
            return false;
        }
        return Objects.equals(personRepo.findPersonByEmail(person.getEmail()).getPassword(), person.getPassword());
    }

    public String favoritePosts(Long userId, Long postId){
        Person user = personRepo.findById(userId).get();
        PostModel post = postRepo.findById(postId).get();

        user.getFavorites().add(post);
        personRepo.save(user);

        return post.getPostText() + " added to favorites for: " + user.getFirstName();
    }

    public String addFriends(Long userId, Long friendId){
        Person user = personRepo.findById(userId).get();
        Person friend = personRepo.findById(friendId).get();

        if(userId.equals(friendId)){
            return "Please note that you cannot add yourself.";
        }

        user.getFriends().add(friend);
        personRepo.save(user);

        return friend.getFirstName() + " has now been added to your friends list, " + user.getFirstName();
    }

    public List<PostModel> getFavoritePosts(Long userId){
        Person user = personRepo.findById(userId).get();

        return user.getFavorites();
    }

    public List<Person> showFriendsList(Long userId){
        Person user = personRepo.findById(userId).get();

        return user.getFriends();
    }

//    public List<String> testtt(Long userId){
//        Person user = personRepo.findById(userId).get();
//
//        ArrayList<String> friendsPosts = new ArrayList<>();
//
//        for(friend : user.getFriends()){
//            friendsPosts.add(friend.)
//        }
//    }
}
