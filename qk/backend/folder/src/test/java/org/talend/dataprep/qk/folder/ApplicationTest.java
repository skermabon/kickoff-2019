package org.talend.dataprep.qk.folder;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ApplicationTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/folder")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}