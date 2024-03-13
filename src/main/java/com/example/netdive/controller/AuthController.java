package com.example.netdive.controller;

import com.example.netdive.dto.AccessToken;
import com.example.netdive.dto.AccessTokenRequest;
import com.example.netdive.dto.BoardDTO;
import com.example.netdive.dto.Ecremmoce;
import com.example.netdive.service.AuthService;
import com.example.netdive.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RequestMapping("/api/v1/oauth2")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /*@PostMapping(value = "/token", consumes="application/x-www-form-urlencoded", produces = "application/json")
    public AccessToken registContent(@RequestParam String client_id, @RequestParam Integer timestamp, @RequestParam String grantType, @RequestParam String client_secret_sign, @RequestParam String type, @RequestParam String account_id) throws Exception{

        AccessTokenRequest accessTokenRequest = AccessTokenRequest.builder()
                .clientId(client_id)
                .timestamp(timestamp)
                .grantType(grantType)
                .clientSecretSign(client_secret_sign)
                .type(type)
                .accountId(account_id)
                .build();

        return authService.publishToken(accessTokenRequest);
    }*/

    @PostMapping(value = "/token", consumes="application/x-www-form-urlencoded", produces = "application/json")
    public AccessToken authorizeJWTToken(@RequestParam String id, @RequestParam String password) {

        AccessToken token = authService.publishToken(id, password);

        return token;
    }

    @GetMapping(value = "/valid/token")
    public boolean authorizeJWTToken(@RequestParam String accessToken) {

        boolean valid = authService.checkValidToken(accessToken);

        return valid;
    }

    /*@GetMapping("/content")
    public BoardDTO getContent(@RequestParam Long contentId) {

        return contentService.readContent(contentId);
    }*/
   /* @PutMapping("/{contentId}")
    public void updateContent(@PathVariable Long contentId, @RequestBody BoardDTO boardDTO) {
        contentService.updateContent(contentId, boardDTO);
    }*/


    @GetMapping("/api/TomsOrder/test")
    public Map<String,Object> getOrderListEcremmoce(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam String companyId, @RequestParam String status, @RequestParam String startDate, @RequestParam String endDate, @RequestParam String platform) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("/Users/diormiss/IdeaProjects/BulletinBoard/src/main/resources/ecremmoceSampleOneData.json"), Map.class);
    }


    @PostMapping("/api/TomsOrder/ArrangeShipment")
    public Map<String,Object> arrangeShipment(@RequestBody Ecremmoce ecremmoce) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("/Users/diormiss/IdeaProjects/BulletinBoard/src/main/resources/ecremmoceTrackingNo.json"), Map.class);
    }


}
