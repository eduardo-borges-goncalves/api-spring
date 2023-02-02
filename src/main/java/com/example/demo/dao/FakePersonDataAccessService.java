package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private  static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> searchPeople() {
        return DB;
    }
}
