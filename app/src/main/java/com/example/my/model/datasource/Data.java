package com.example.my.model.datasource;

import com.example.my.model.entities.*;

import java.util.List;

public class Data {
    List<Pack> packs;
    List<Person> persons;

    public Data() {
    }

    public Person getPersonByID(String id) throws Exception {
        for(Person person:persons)
        {
            if(person.getId()==id)
                return new Person(person);
        }
        throw new Exception("This person does not exist");
    }
}
