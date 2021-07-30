package guru.springframework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.api.model.Datum;
import guru.springframework.api.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class SpringRestClientExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestClientExamplesApplication.class, args);
    }
}
