package com.carpio.cuentasbancariascucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.AfterAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountSteps {
    private Response response;

    @LocalServerPort
    private int port;

    @PostConstruct
    public void init() {
        RestAssured.port = port;
    }

    @AfterAll
    public static void tearDown() {
        RestAssured.reset();
    }

    @Given("que existe una cuenta de {string} con saldo {double}")
    public void existeAccount(String client, Double amount) {
    }

    @When("consulto el saldo de {string}")
    public void consultoSaldo(String client) {
        response = given().request().contentType(ContentType.JSON)
                .when().get("/accounts/balance/{client}", client);
    }

    @Then("el sistema debe devolver un saldo de {double}")
    public void validarSaldo(Double expectedBalance) {
        response.then().statusCode(200);
        Double actualBalance = response.getBody().as(Double.class);
        assertEquals(expectedBalance, actualBalance);
    }

    @When("deposito {double} en la cuenta de {string}")
    public void depositoEnCuenta(Double amount, String client) {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .post("/accounts/deposit?client={client}&amount={amount}", client, amount);
    }

    @Then("el saldo de la cuenta de {string} debe ser {double}")
    public void validarNuevoSaldo(String client, Double expectedBalance) {
        response.then().statusCode(200);
        Double actualBalance = response.getBody().as(Double.class);
        assertEquals(expectedBalance, actualBalance);
    }
}
