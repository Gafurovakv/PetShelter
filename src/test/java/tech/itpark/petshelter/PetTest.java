package tech.itpark.petshelter;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import tech.itpark.petshelter.model.PetDto;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetTest {
    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void getAllThenOk() {
        when().request("GET", "/pets/").then().statusCode(200);
    }

    @Test
    public void getAllNotFound() {
        when().request("GET", "/wrongUrl").then().statusCode(404);
    }

    @Test
    public void getByIdNotFound() {
        when().request("GET", "/pets/100").then().statusCode(200);
    }

    @Test
    public void getByIdThenOk() {
        when().request("GET", "/pets/1").then().statusCode(200);
    }

    @Test
    public void searchThenOk() {
        when().request("GET", "/pets/search?type=dog&age=1&gender=girl").then().statusCode(200);
    }

    @Test
    public void searchBadRequest() {
        when().request("GET", "/pets/search?type=dog&age=10").then().statusCode(400);
    }

    @Test
    public void createdNewPet() {
        with().body(new PetDto(5, "Busya",
                "kat",
                1,
                "girl",
                3))
                .contentType("application/json")
                .when()
                .request("POST", "/pets/save")
                .then()
                .statusCode(200);
    }

    @Test
    public void updatePetThenOk() {
        with().body(new PetDto(2, "Bublik",
                1))
                .contentType("application/json")
                .when()
                .request("POST", "/pets/update/")
                .then()
                .statusCode(200);
    }

    @Test
    public void removeThenOk() {
        when().request("DELETE", "/pets/remove/3").then().statusCode(200);
    }
}
