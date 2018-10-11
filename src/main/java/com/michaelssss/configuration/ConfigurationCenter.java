package com.michaelssss.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * 配置中心，将所有的配置项放入数据库中管理，配置文件中只包含了非业务相关的配置，如数据库连接等
 *
 * @author Michaelssss
 * @date 2018年10月11日
 */
@Service
public class ConfigurationCenter {

  private ConfigurationCollectionRepository repository;
  private Collection<ConfigurationCollection> collections;

  @Autowired
  public ConfigurationCenter(ConfigurationCollectionRepository repository) {
    this.repository = repository;
  }

  @PostConstruct
  public void init() {
    this.collections = repository.findAll();
  }

  public String getValue(String key) {
    return getValue("", key);
  }

  public String getValueOrDefault(String key, String defaultValue) {
    return getValue(key) == null ? defaultValue : getValue(key);
  }

  public String getValueOrDefault(String subsystem, String key, String defaultValue) {
    return getValue(subsystem, key) == null ? defaultValue : getValue(subsystem, key);
  }

  /**
   * 若key存在则返回，否则返回null
   */
  public String getValue(String subSystem, String key) {
    String result = null;
    Optional<ConfigurationCollection> configurationCollection1 =
        this.collections
            .parallelStream()
            .filter(
                configurationCollection -> configurationCollection.getSubSystem().equals(subSystem))
            .findFirst();
    if (configurationCollection1.isPresent()) {
      result = configurationCollection1.get().getValue(key);
    }
    return result;
  }

  public void addKeyValue(String subSystem, String key, String value) {
    ConfigurationCollection sample = new ConfigurationCollection();
    sample.setSubSystem(subSystem);
    ConfigurationCollection configurationCollection = null;
    if (repository.exists(Example.of(sample))) {
      configurationCollection = new ConfigurationCollection();
      configurationCollection.setSubSystem(subSystem);
      configurationCollection.setKeyValueObjects(new ArrayList<>());
      configurationCollection.addKeyValue(key, value);

    } else {
      configurationCollection = repository.findOne(Example.of(sample)).get();
      configurationCollection.addKeyValue(key, value);
    }
    this.repository.saveAndFlush(configurationCollection);
    init();
  }

  public void addKeyValue(String key, String value) {
    addKeyValue("", key, value);
  }

  public void deleteKey(String key) {
    deleteKey("", key);
  }

  public void deleteKey(String subSystem, String key) {
    ConfigurationCollection sample = new ConfigurationCollection();
    sample.setSubSystem(subSystem);
    Optional<ConfigurationCollection> configurationCollection =
        repository.findOne(Example.of(sample));
    if (configurationCollection.isPresent()) {
      configurationCollection.get().deleteKey(key);
      init();
    }
  }
}
