package com.example.netdive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessTokenRequest {

    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("grant_type")
    private String grantType;
    @JsonProperty("client_secret_sign")
    private String clientSecretSign;
    @JsonProperty("type")
    private String type;
    @JsonProperty("account_id")
    private String accountId;

}
