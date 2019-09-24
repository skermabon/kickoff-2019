package org.talend.kickoff.mn.common;

import io.micronaut.http.client.annotation.Client;

@Client(id = "person", path = "/person/v1/persons")
public interface PersonClient extends PersonOperations {

}
