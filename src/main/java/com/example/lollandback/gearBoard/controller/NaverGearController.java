package com.example.lollandback.gearBoard.controller;


        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.http.RequestEntity;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.client.RestTemplate;
        import org.springframework.web.util.UriComponentsBuilder;

        import java.net.URI;
        import java.nio.charset.StandardCharsets;
        import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/gear")
public class NaverGearController {



    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.ps}")
    private String naverClientSecret;

    @GetMapping("naver")
    public String naver() {

        String query = "갈비집";
        String encode = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));

        URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com/")
                .path("v1/search/news.json")
                .queryParam("query", "전자장비")
                .queryParam("display", 16)
                .queryParam("start", 1)
                .queryParam("sort", "date")
                .encode()
                .build()
                .toUri();

        log.info("uri : {}", uri);
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", naverClientId)
                .header("X-Naver-Client-Secret", naverClientSecret)
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        return result.getBody();
    }



    @GetMapping("gearnews")
    public String navers() {

        String query = "갈비집";
        String encode = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));

        URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com/")
                .path("v1/search/news.json")
                .queryParam("query", "컴퓨터장비")
                .queryParam("display", 15)
                .queryParam("start", 1)
                .queryParam("sort", "date")
                .encode()
                .build()
                .toUri();

        log.info("uri : {}", uri);
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", naverClientId)
                .header("X-Naver-Client-Secret", naverClientSecret)
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        return result.getBody();
    }


}

