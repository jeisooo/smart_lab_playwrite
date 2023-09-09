package test.API;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
public class shares {
    static String name;
    static Integer price;
    static float openPrice;
    static float closePrice;
    void setup(){
        RestAssured.baseURI = "https://postman-echo.com/get?foo1=bar1&foo2=bar2";

    }
    /*static Boolean checkPrice(){
        given()
                .
        return get("/lotto").then().body("lotto.lottoId", equalTo(5));
    }*/
    @Test
    public void shouldBeMoex(){
        RestAssured.baseURI = "https://iss.moex.com/iss";
        RestAssured.
                when().get(baseURI+"/securities.json?q=MOEX").
                then().assertThat().statusCode(200).
                and().body("securities.data[0][1]", is("IMOEX"));
    }
    @Test
    public void shouldBeGetWebhook(){
        RestAssured.baseURI = "https://webhook.site/b86785ef-166e-42c9-8e81-f56e87e81ec0";
        RestAssured.
                when().get(baseURI).
                then().assertThat().statusCode(401);
                //and().body("securities.data[0][1]", is("IMOEX"));
    }

    @Test
    public void shouldBeGetMockoon(){
        RestAssured.baseURI = "https://localhost:3001";
        RestAssured.
                given().relaxedHTTPSValidation().
                when().get(baseURI).
                then().assertThat().statusCode(200).
                and().body("test", is("OK"));
    }

    @Test
    public void shouldBePostMockoon(){
        RestAssured.baseURI = "https://localhost:3001";
        String requestBody = "{\"name\":\"John\",\"email\":\"john@example.com\"}";
        RestAssured.
                given()
                    .relaxedHTTPSValidation()
                    .header("type","test-header-2")
                    //.header("another-type","header2")
                    .contentType(ContentType.JSON)
                    .body(requestBody).
                when().post("/address").
                then().assertThat().statusCode(200).
                and().body("id", is(6));
    }

}

