package guru.springframework.service;

import guru.springframework.api.model.Datum;
import guru.springframework.api.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ApiService {

    List<Datum> getData(Integer limit);

    Flux<Datum> getData(Mono<Integer> limit);
}
