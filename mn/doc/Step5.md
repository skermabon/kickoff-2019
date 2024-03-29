# Casablanca Workshop / Micronaut.

## Step 5 - Messages between microservices

Checkout the step5-start branch.

```shell
$ git checkout step5-start
```

We use Kafka for communication between microservices.

We complete the comicbook document by adding persons that contribute to this comicbook as author, artist, penciller, letterer, etc...

A comicbook document look like this:

```json
{
    "_id" : "b9f60ef3-2497-40c7-b96d-b5ebe2f6d9d9",
    "name" : "Northstar",
    "persons" : {
        "WRITER" : [ 
            {
                "_id" : "67a4980e-e0d4-4355-be1d-96b5cd67064e",
                "firstname" : "Chris",
                "lastname" : "Claremont"
            }
        ],
        "ARTIST" : [ 
            {
                "_id" : "67a4980e-e0d4-4355-be1d-96b5cd67064e",
                "firstname" : "John",
                "lastname" : "Byrne"
            }
        ]
    }
}
```
So if we update a person by using the person microservice, we also want to update the comicbook document that reference this person. We use Kafka to produce a message in the person microservice, and consume this message on comicbook microservice.

We add a Kafka message producer as in interface

```java
@KafkaClient
public interface PersonProducer {

    @Topic("person")
    void sendPerson(@KafkaKey String id, Person person);
}
```

And we inject this producer to the controller, to send a message when we update a person.

```java
    @Override
    public HttpResponse<Person> put(@PathVariable String id, @Body Person person) {
        Person updatedPerson = personRepository.update(person).blockingGet();
        personProducer.sendPerson(updatedPerson.getId(), updatedPerson);
        return HttpResponse.ok(updatedPerson);
    }

```

On the comicbook microservice, we add a listener to read messages and process them.

```java
@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class PersonListener {

    private final ComicbookService comicbookService;

    public PersonListener(ComicbookService comicbookService) {
        this.comicbookService = comicbookService;
    }

    @Topic("person")
    public void receivePerson(@KafkaKey String id, Person updatedPerson) {
        comicbookService.updatePerson(updatedPerson);
    }
}
```

### Run and tests

Now we can run a docker-compose of a kafka and a zookeeper docker images, present in the branch.

```shell
$ docker-compose -f kafka.yaml
```

And we launch all services. We can add a person to a comicbook.

```shell
curl -X POST \
  'http://localhost:7001/comicbook/v1/comicbooks/b9f60ef3-2497-40c7-b96d-b5ebe2f6d9d9/writer/?=' \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{"id": "5f4cdbed-62ba-44e7-9f1e-9959277cfada", "firstname":"Jerry", "lastname": "Siegel"}'
```

If I update the person like this.

```shell
curl -X PUT \
  http://localhost:7002/person/v1/persons/5f4cdbed-62ba-44e7-9f1e-9959277cfada \
  -H 'Content-Type: application/json' \
  -d '{"id":"5f4cdbed-62ba-44e7-9f1e-9959277cfada", "firstname":"JERRY", "lastname":"SIEGEL"}'
```

Then, when I get this comicbook, the person was updated.

```shell
curl -X GET \
  http://localhost:7001/comicbook/v1/comicbooks/b9f60ef3-2497-40c7-b96d-b5ebe2f6d9d9
```

### Exercice

Now, when you add a comicbook with a person without id, the comicbook service call the person service to create this person. And a message send by the person service is receive by the comicbook service that update document to add the id for person with the same firstname and lastname.

