package test.config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Iterator;


import java.io.*;
import java.net.URL;

public class ConfigReader {
    JSONParser parser;
    public static String getProperty(String key) {
        JSONParser parser = new JSONParser();

        String value = null;
        try (Reader reader = new FileReader("/home/jeis/Documents/Рабочие/Тестирование/Playwright/smart_lab_playwrite/src/test/java/test/config/config.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            value = (String) jsonObject.get(key);
            return value;
            //System.out.println(name);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            return value;
        }
    }

}
