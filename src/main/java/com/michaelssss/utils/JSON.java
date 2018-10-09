package com.michaelssss.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSON {

  public static String toJSONString(Object o) {
    String result = "";
    try {
      ObjectMapper mapper = new ObjectMapper();
      result = mapper.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      log.info("parse object to json failed ,because ", e);
    }

    return result;
  }

  public static Map<String, Object> parseJSONString2Map(String json) {
    Map<String, Object> map = new HashMap<>();
    try {
      ObjectMapper mapper = new ObjectMapper();
      map = mapper.readValue(json, HashMap.class);
    } catch (IOException e) {
      log.info("parse string to mapperObject failed ,because ", e);
    }
    return map;
  }
}
