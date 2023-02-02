package com.example.demo.api;

import com.example.demo.domain.model.Person;
import com.example.demo.domain.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("person")
@RestController
public class PersonController {
    private  final PersonService personService;

    public PersonController( PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public  void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getPeople() {
        return personService.getPeople();
    }
}
