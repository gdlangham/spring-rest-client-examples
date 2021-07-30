package guru.springframework.service;

import guru.springframework.api.model.Datum;
import guru.springframework.api.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiServiceImplTest {

    @Autowired
    ApiService apiService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetUsers() {
        List<Datum> users = apiService.getData(4);
        assertEquals(4, users.size());
    }

    @Test
    public void testGetUsersReactive() {
        Flux<Datum> data = apiService.getData(Mono.just(3));
        assertEquals(3, data.collectList().block().size());
    }
}