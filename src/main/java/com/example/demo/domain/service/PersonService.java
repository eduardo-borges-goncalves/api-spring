package com.example.demo.domain.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.domain.model.Person;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonDao personDao;

    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getPeople(){
        return personDao.searchPeople();
    }
}
