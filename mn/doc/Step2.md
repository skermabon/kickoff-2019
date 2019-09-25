# Casablanca Workshop / Micronaut.

## Step 2 : Create project and run a service

I create the Micronaut project (only maven modules that are microservice, not non microservice modules), on an empty directory.
```shell
mn create-federation /
--build=maven /
--lang=java /
--services=org.talend.kickoff.mn.api.api,org.talend.kickoff.mn.person.person,org.talend.kickoff.mn.common.common,org.talend.kickoff.mn.comicbook.comicbook /
--stacktrace /
backend /
--features graal-native-image,mongo-reactive,http-client
```

If you have some errors, or if you are late, you can clone the repository and checkout the step2 branch

```shell
git clone https://github.com/skermabon/kickoff-2019.git​ 
git checkout step2
cd kickoff-2019/mn
```

Build by Intellij or by maven: 
~~~~
cd backend
mvn clean install
~~~~

Run a class Application (for example the Application in maven module comicbook) or run in a shell:
```shell
cd comicbook
java -jar target/comicbook-0.1.jar
```

Build a native image with GraalVM:
* Use GraalVM as current JVM: `sdk use java 19.2.0-grl`
* Install the GraalVM tool, native-image, that generate native image from a jar: `gu install native-image` 
* Create a native image: `native-image --no-server -cp target/comicbook-0.1.jar` if you have the following error:
~~~~
Error: Basic header file missing (<zlib.h>). Make sure headers are available on your system.
Error: Use -H:+ReportExceptionStackTraces to print stacktrace of underlying exception
Error: Image build request failed with exit status 1
~~~~

Add the library zlib: `sudo apt-get install zlib1g-dev`

Now you can run the application: `./comicbook`