package com.michaelssss.configuration;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sys_config_collection")
@Data
class ConfigurationCollection {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;

  private String subSystem;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Collection<ConfigurationKeyValueObject> keyValueObjects;

  String getValue(String key) {
    key = Base64.getEncoder().encodeToString(key.getBytes(Charset.forName("UTF-8")));
    for (ConfigurationKeyValueObject object : this.keyValueObjects) {
      if (object.getKey().equals(key)) {
        return new String(Base64.getDecoder().decode(object.getValue()), Charset.forName("UTF-8"));
      }
    }
    return null;
  }

  void addKeyValue(String key, String value) {
    Base64.Encoder encoder = Base64.getEncoder();
    ConfigurationKeyValueObject keyValueObject = new ConfigurationKeyValueObject();
    keyValueObject.setKey(encoder.encodeToString(key.getBytes(Charset.forName("UTF-8"))));
    keyValueObject.setValue(encoder.encodeToString(value.getBytes(Charset.forName("UTF-8"))));
    this.keyValueObjects.add(keyValueObject);
  }

  void deleteKey(String key) {
    key = Base64.getEncoder().encodeToString(key.getBytes(Charset.forName("UTF-8")));
    ConfigurationKeyValueObject object1 = null;
    for (ConfigurationKeyValueObject object : this.keyValueObjects) {
      if (object.getKey().equals(key)) {
        object1 = object;
      }
    }
    if (null != object1) {
      this.keyValueObjects.remove(object1);
    }
  }
}
