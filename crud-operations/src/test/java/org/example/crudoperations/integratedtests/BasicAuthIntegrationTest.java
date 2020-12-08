package org.example.crudoperations.integratedtests;

import org.example.crudoperations.CrudOperationsApplication;
import org.example.crudoperations.entity.Role;
import org.example.crudoperations.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudOperationsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicAuthIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    TestRestTemplate restTemplate=new TestRestTemplate();

    HttpHeaders headers=new HttpHeaders();


    @Test
    public void testRetrieveCurrentUserDetail()  {


        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.withBasicAuth("admin","admin").exchange(
                createURLWithPort("/user"),
                HttpMethod.GET, entity, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());


    }


    @Test
    public void testAuthentication()  {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.withBasicAuth("admin","admin").exchange(
                createURLWithPort("/basicauth"),
                HttpMethod.GET, entity, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("You are authenticated", response.getBody());

    }
    @Test
    public void testAuthenticationFailure()  {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.withBasicAuth("admin","admin123").exchange(
                createURLWithPort("/basicauth"),
                HttpMethod.GET, entity, String.class);

        Assert.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

    }
    @Test
    public void testSignUp()  {
        User testUser=new User("ankita","email","ankita", Arrays.asList(new Role("ROLE_USER")));
        HttpEntity<User> entity = new HttpEntity<>(testUser, headers);


        ResponseEntity<User> response = restTemplate.exchange(
                createURLWithPort("/signup"),
                HttpMethod.POST, entity, User.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(testUser.getUserId(), response.getBody().getUserId());



    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}