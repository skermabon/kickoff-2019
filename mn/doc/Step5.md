# Casablanca Workshop / Micronaut.

## Step 5 - Messages between microservices

We use Kafka for communication between microservices.

We complete the comicbook document by adding persons that contribute to this comicbook as author, artist, penciller, letterer, etc...

A comicbook document look like this:

```json
{
    "_id" : "b9f60ef3-2497-40c7-b96d-b5ebe2f6d9d9",
    "name" : "Action Comics",
    "persons" : {
        "WRITER" : [ 
            {
                "_id" : "67a4980e-e0d4-4355-be1d-96b5cd67064e",
                "firstname" : "Jerry",
                "lastname" : "Siegel"
            }
        ],
        "ARTIST" : [ 
            {
                "_id" : "67a4980e-e0d4-4355-be1d-96b5cd67064e",
                "firstname" : "Joe",
                "lastname" : "Shuster"
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

