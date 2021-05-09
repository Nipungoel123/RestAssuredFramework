package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import java.io.*;
import java.util.Properties;

public class utils{
    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws FileNotFoundException {
        if (req == null) {
            PrintStream logging = new PrintStream(new FileOutputStream("logging.txt"));
            RestAssured.baseURI = "https://rahulshettyacademy.com";

            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(logging))
                    .addFilter(ResponseLoggingFilter.logResponseTo(logging))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }


    public String getGlobalValue(String Key){
        Properties prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/Users/nipungoel/Desktop/workspace2/gityo/restAssuredFrameworkTest/src/test/java/resources/global.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(Key);
    }

    public String getJsonPath(Response response, String key){
        String res = response.asString();
        JsonPath js = new JsonPath(res);
        return js.get(key).toString();

    }
}
