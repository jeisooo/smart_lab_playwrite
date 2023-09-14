package test.API;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.examples.Utils;
import com.google.gson.Gson;
//import com.networknt.schema.JsonSchema;
import com.networknt.schema.SpecVersion;

import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.networknt.schema.ValidationMessage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import okhttp3.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.tools.httpClientOkHttp;

import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Test API")
@Feature("Test Shares endpoin")
public class testShares {

    private static final String BASE_URL = "http://localhost:3001";
    Gson gson = new Gson();
    httpClientOkHttp client = new httpClientOkHttp();




    @Test
    @DisplayName("Test mock schema")
    @Description("Test / root endpoint. 200 code /n Test 'value' for 'test' field")
    public void shouldReturnGetMockoon() throws IOException{
        Response response  = client.getResponse(BASE_URL);
        String bodyResponse = client.getBody(BASE_URL);
        SimpleEntity entity = gson.fromJson(bodyResponse, SimpleEntity.class);
        System.out.println(response);
        assertEquals(response.code(),200);
        System.out.println(entity.test);
        assertEquals(entity.test,"OK");
    }

    @Test
    @Story("test /")
    @DisplayName("Test schema for / root endpoint")
    @Description("Test mock schema in mockoon")
    public void testJsonResponseAgainstSchema() throws IOException, ProcessingException {
        //String url = "https://api.example.com/my-api-endpoint";
        String responseBody = client.getBody(BASE_URL);
        // Load the JSON schema from a file
        final File jsonSchemaFile = new File("resources/shemas/test_schema.json");
        final URI uri = jsonSchemaFile.toURI();
        // Parse the schema
        JsonSchema jsonSchema = JsonSchemaFactory.byDefault().getJsonSchema(uri.toString());
        // Validate the response against the schema
        ProcessingReport report = jsonSchema.validate(JsonLoader.fromString(responseBody));
        if (report.isSuccess()) {
            System.out.println("JSON response is valid against the schema.");
        } else {
            System.err.println("JSON response is NOT valid against the schema:");
            System.err.println(report);
        }
    }

    @Test

    @Story("test /shares")
    @DisplayName("Test schema for /shares endpoint")
    @Description("Test schema for /shares")
    public void testJsonSchemaGetShare()throws IOException, ProcessingException {

        String responseBody = client.getBody(BASE_URL+"/shares");
        // Load the JSON schema from a file
        final File jsonSchemaFile = new File("resources/shemas/shares_schema.json");
        final URI uri = jsonSchemaFile.toURI();
        // Parse the schema
        JsonSchema jsonSchema = JsonSchemaFactory.byDefault().getJsonSchema(uri.toString());
        // Validate the response against the schema
        ProcessingReport report = jsonSchema.validate(JsonLoader.fromString(responseBody));
        if (report.isSuccess()) {
            System.out.println("JSON response is valid against the schema.");
        } else {
            System.err.println("JSON response is NOT valid against the schema:");
            System.err.println(report);
        }
    }

}
