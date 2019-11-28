package com.example.my.model.datasource;

import android.location.Location;

import com.example.my.model.entities.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Data {
    List<Pack> packs;
    List<Person> persons;

    public Data() {
        packs=new ArrayList<Pack>();
        persons=new ArrayList<Person>();
        for (int i = 0; i <10 ; i++) {
            addPerson(new Person("id"+i,"fname"+i,"lname"+i,"pnumber"+i,"@"+i,
                    new AddressAndLocation()));
        }


    }

    public Person getPersonByID(String id) throws Exception {
        for(Person person:persons)
        {
            if(person.getId()==id)
                return new Person(person);
        }
        throw new Exception("This person does not exist");
    }

    public void addPack(Pack pack) {
        packs.add(pack);
    }

    public void addPerson(Person person) {
       persons.add(person);

    }
}
