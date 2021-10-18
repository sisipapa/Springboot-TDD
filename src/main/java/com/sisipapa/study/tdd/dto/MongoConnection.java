package com.sisipapa.study.tdd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class MongoConnection {
    String host;
    Integer count;
}
