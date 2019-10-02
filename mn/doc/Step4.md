# Casablanca Workshop / Micronaut.

## Step 4 - API call other services

The API service will call Person and Comicbook services. Checkout the step4-start branch of the project.

```shell
$ git checkout step4-start
```


### Operations interface

We create an interface to declare all endpoints for Comicbook service:

```java
public interface ComicbookOperations {

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    HttpResponse<List<Comicbook>> list();

    [...]
}
```

### We update the Comicbook Controller

```java
@Controller("/comicbook/v1/comicbooks")
public class ComicbookController implements ComicbookOperations {

    private ComicbookRepository comicbookRepository;

    public ComicbookController(ComicbookRepository comicbookRepository) {
        this.comicbookRepository = comicbookRepository;
    }

    @Override
    public HttpResponse<List<Comicbook>> list() {
        return HttpResponse.ok(comicbookRepository.list().blockingGet());
    }

    [...]
```

### We add the Comicbook client.

The client is an interface that extends the previous operation interface. We set the service we will use, and the path.

```java
@Client(id = "comicbook", path = "/comicbook/v1/comicbooks")
public interface ComicbookClient extends ComicbookOperations {

}
```

We add configuration to use this client

```yaml
micronaut:
  http:
    services:
      comicbook:
        urls:
          - http://localhost:7001/
```

### We start call the Comicbook service from the API service

```java
@Controller("/api/v1/comicbooks/")
public class APIComicbookController {

    private final ComicbookClient client;

    public APIComicbookController(ComicbookClient client) {
        this.client = client;
    }

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<List<Comicbook>> list() {
        return client.list();
    }
}
```

### Exercice (10 minutes)

Run the both services, Comicbook (port 7001) and API (port 7000), and test the API endpoint.

```shell
$ curl -X GET \
  http://localhost:7000/api/v1/comicbooks
```

And now, add the API endpoint to POST a comicbook.

### Solution

Checkout the branch step4-final.

```shell
$ git checkout step4-final
```