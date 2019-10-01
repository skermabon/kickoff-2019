package org.talend.kickoff.mn.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Comicbook {

    private String id;

    private String name;

    private Map<String, List<Person>> persons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Person> getAllPersons() {
        if (persons == null  || persons.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList<Person> list = new ArrayList<>();
        for (String key: persons.keySet()) {
            list.addAll(persons.get(key));
        }
        return list;
    }

    public Map<String, List<Person>> getPersons() {
        return persons;
    }

    public void setPersons(Map<String, List<Person>> persons) {
        this.persons = persons;
    }

    public void addPerson(Role role, Person person) {
        if (persons == null) {
            persons = new HashMap<>();
        }
        if (persons.get(role.toString()) == null) {
            persons.put(role.toString(), Collections.singletonList(person));
        }
        else {
            persons.get(role.toString()).add(person);
        }
    }
}
