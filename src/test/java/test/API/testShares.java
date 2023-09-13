package test.API;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import test.tools.httpClientOkHttp;

import java.io.IOException;

public class testShares {
    httpClientOkHttp client = new httpClientOkHttp();
    private static final String BASE_URL = "https://localhost:3001";
    String response  = client.run(BASE_URL);

    public testShares() throws IOException {
    }
}
