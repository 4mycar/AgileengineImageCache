package com.serdyukov.agileengine.services.external;

import com.serdyukov.agileengine.dto.from_server.AuthResponse;
import com.serdyukov.agileengine.entity.Item;
import com.serdyukov.agileengine.exeptions.NotAuthorizedExeption;
import com.serdyukov.agileengine.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestService implements IRestService {

    @Value("${api.key}")
    private String API_KEY;
    @Value("${auth.uri}")
    private String AUTH_URI;
    @Value("${images.uri}")
    private String IMAGES_URI;

    @Autowired
    private final ItemRepository itemRepository;

    @Override
    public AuthResponse getAuth() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = String.format("{ \"apiKey\": \"%s\" }", API_KEY);

        HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);

        ResponseEntity<AuthResponse> exchange =
                restTemplate.exchange(AUTH_URI, HttpMethod.POST, entity, AuthResponse.class);

        if (exchange.getStatusCode().value() != 200){
            throw new NotAuthorizedExeption(exchange.getStatusCode().value(),
                    exchange.getStatusCode().getReasonPhrase());
        }

        return exchange.getBody();
    }

    @Override
    public Map<Integer, List> getImages(String token) throws URISyntaxException {
        HashMap result = new HashMap();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        boolean hasMore = true;
        int page = 1;


        ResponseEntity<Object> exchange = null;

        while (hasMore) {
            exchange = restTemplate.exchange(IMAGES_URI + "?page=" + page, HttpMethod.GET, entity, Object.class);

            if (exchange.getBody() != null) {
                Map body = (Map) exchange.getBody();

                result.put((Integer) body.get("page"), (List) body.get("pictures"));

                hasMore = (boolean) body.get("hasMore");
                page++;
            }
        }
        return result;
    }

    @Override
    public Item getImageInfo(String id, String token) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<Item> item =
                restTemplate.exchange(IMAGES_URI + "/" + id, HttpMethod.GET, entity, Item.class);

        return item.getBody();
    }

    @Override
    public void updateData(String token) {
        try {
            Map<Integer, List> pageImages = getImages(token);

            for (Map.Entry<Integer, List> elem : pageImages.entrySet()) {
                for (Object o : elem.getValue()) {

                    Map image = (Map) o;

                    Item imageInfo = getImageInfo((String) image.get("id"), token);

                    Item item = itemRepository
                            .findById((String) image.get("id"))
                            .orElse(new Item((String) image.get("id")));

                    item.setAuthor(imageInfo.getAuthor());
                    item.setCamera(imageInfo.getCamera());
                    item.setCropped_picture(imageInfo.getCropped_picture());
                    item.setFull_picture(imageInfo.getFull_picture());
                    item.setTags(imageInfo.getTags());
                    item.setPage(elem.getKey());

                    itemRepository.save(item);
                }
            }
        } catch (URISyntaxException e) {
            log.info(e.getMessage());
        }
    }
}
