# Casablanca Workshop / Micronaut.

## Step 3 - Controller and repository

We adding a controller, and a repository for managing Comicbook. Checkout the step3-start branch of the project.

```shell
$ git checkout step3-start
```

### The Pojo

A simple Pojo that represent a comicbook.

```java
public class Comicbook {

    private String id;

    private String name;

    [...]
}
```

### The repository

A minimal repository for comicbook. We use a reactive mongo client and implement a simple list method.

```java
@Singleton // The repository is a Singleton
public class ComicbookRepository {

    public static final String COMICBOOK_COLLECTION = "comicbook";

    private final MongoClient mongoClient;

    public ComicbookRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Single<List<Comicbook>> list() {
        return Flowable.fromPublisher(getCollection().find()).toList();
    }

    private MongoCollection<Comicbook> getCollection() {
        return mongoClient
                .getDatabase("kickoff")
                .getCollection(COMICBOOK_COLLECTION, Comicbook.class);
    }
}
```

### The controller

A minimal controller for comicbook. We inject the repository and add an endpoint to get the list of comicbooks.

```java
@Controller("/comicbook/v1/comicbooks")
public class ComicbookController {

    private ComicbookRepository comicbookRepository;

    public ComicbookController(ComicbookRepository comicbookRepository) {
        this.comicbookRepository = comicbookRepository;
    }

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<List<Comicbook>> list() {
        return HttpResponse.ok(comicbookRepository.list().blockingGet());
    }
}
```
### Test the endpoint

A unit test is created to test the endpoint. When we add the feature 

```java
@MicronautTest
public class ComicbookControllerTest {

    @Inject
    EmbeddedServer server;

    @Inject @Client("/comicbook/v1/comicbooks")
    HttpClient client;

    @Test 
    void emptyList() throws Exception {
        String retrieve = client.toBlocking().retrieve(HttpRequest.GET(""), String.class);
        assertNotNull(retrieve);
        assertEquals("[]", retrieve);
    }
}
```
You can run this test by Maven or in your IDE. The test succeed.

### Build and run your service and test the endpoint.

Build and run the service by shell:
```shell
$ mvn clean install
$ java -jar target/comicbook-0.1.jar
```

or by your IDE. And test by shell:

```shell
$ curl -X GET \
  http://localhost:7001/comicbook/v1/comicbooks \
  -H 'Accept: application/json' \
  -H 'Host: localhost:7001' 
```

or by Postman.

### Exercice (15 minutes)

Now you can add a new endpoint like a POST of one resource. Annotation for a POST endpoint is `@Post` and annotation to get the content of the request is `@Body`.

### A solution

Checkout the branch step3-final.

```shell
$ git checkout step3-final
```