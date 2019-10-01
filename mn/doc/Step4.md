# Casablanca Workshop / Micronaut.

## Step 4 - API call other services

The API service will call Person and Comicbook services. Checkout the step4-start branch of the project. Get the branch `step4-start`.

```shell
$ git checkout step4-start
```


### Operations interface

We create an interface to declare all endpoints for Comicbook service:

```java
public interface ComicbookOperations {

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    HttpResponse<List<Comicbook>> list();

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    HttpResponse<Comicbook> get(@PathVariable String id);

    @Post(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    HttpResponse<Comicbook> post(@Body Comicbook person);

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    HttpResponse<Comicbook> put(@PathVariable String id, @Body Comicbook comicbook);

    @Delete(value = "/{id}")
    HttpResponse delete(@PathVariable String id);
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

    @Override
    public HttpResponse<Comicbook> get(@PathVariable String id) {
        Comicbook comicbook = comicbookRepository.get(id).blockingGet();
        if (comicbook != null) {
            return HttpResponse.ok(comicbook);
        } else {
            return HttpResponse.notFound();
        }
    }

    @Override
    public HttpResponse<Comicbook> post(@Body Comicbook person) {
        return HttpResponse.created(comicbookRepository.create(person).blockingGet());
    }

    @Override
    public HttpResponse<Comicbook> put(@PathVariable String id, @Body Comicbook comicbook) {
        return HttpResponse.ok(comicbookRepository.update(comicbook).blockingGet());
    }

    @Override
    public HttpResponse delete(@PathVariable String id) {
        comicbookRepository.delete(id);
        return HttpResponse.noContent();
    }
}
```

### We add the Comicbook client interface and declare the service used (comicbook) in configuration

```java
@Client(id = "comicbook", path = "/comicbook/v1/comicbooks")
public interface ComicbookClient extends ComicbookOperations {

}
```

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