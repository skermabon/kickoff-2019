# Casablanca Workshop / Micronaut.

## Step 3 - Controller and repository

We adding a controller, and a repository for managing Comicbook. Checkout the step3-start branch of the project.

```shell
git checkout step3-start
```

### Add a Pojo

```java
public class Comicbook {

    private String id;

    private String name;

    [...]
}
```

### Add a repository

```java
@Singleton
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

### Add an endpoint

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

### Run your service

```shell
java -jar target/comicbook-0.1.jar
```

Now you can add a new endpoint like a GET of one resource (or choose POST a new resource).

### A solution

Checkout the branch step3-final.

```shell
git checkout step3-final
```