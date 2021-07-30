package guru.springframework.service;

import guru.springframework.api.model.Datum;
import guru.springframework.api.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.image.DataBufferInt;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    private RestTemplate restTemplate;


    private final String url;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public List<Datum> getData(Integer limit) {
        UriComponentsBuilder uriComponentsBuilder =
                UriComponentsBuilder
                        .fromUriString(url)
                        .queryParam("_locale", "en_EN")
                        .queryParam("_quantity", limit);

        User user = restTemplate.getForObject(uriComponentsBuilder.toUriString(), User.class);
        return user.getData();
    }

    @Override
    public Flux<Datum> getData(Mono<Integer> limit) {
        return WebClient
                .create(url)
                .get()
                .uri(uriBuilder ->
                        uriBuilder.queryParam("_quantity", limit.toProcessor().block())
                                .queryParam("_locale", "en_EN")
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(User.class))
                .flatMapIterable(User::getData);

    }
}
