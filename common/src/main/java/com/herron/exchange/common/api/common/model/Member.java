package com.herron.exchange.common.api.common.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Member(String memberId) {
}
