package com.jzqh.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 配置中心，将所有的配置项放入数据库中管理，配置文件中只包含了非业务相关的配置，如数据库连接等
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
     *
     * @param subSystem
     * @param key
     * @return
     */
    public String getValue(String subSystem, String key) {
        String result = null;
        for (ConfigurationCollection configurationCollection : this.collections) {
            if (configurationCollection.getSubSystem().equals(subSystem)) {
                result = configurationCollection.getValue(key);
            }
        }
        return result;
    }

    public void addKeyValue(String subSystem, String key, String value) {
        ConfigurationCollection sample = new ConfigurationCollection();
        sample.setSubSystem(subSystem);
        ConfigurationCollection configurationCollection = null;
        if (repository.count(Example.of(sample)) == 0) {
            configurationCollection = new ConfigurationCollection();
            configurationCollection.setSubSystem(subSystem);
            configurationCollection.setKeyValueObjects(new ArrayList<>());
            configurationCollection.addKeyValue(key, value);

        } else {
            configurationCollection = repository.findOne(Example.of(sample));
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
        ConfigurationCollection configurationCollection = repository.findOne(Example.of(sample));
        if (null != configurationCollection) {
            configurationCollection.deleteKey(key);
            init();
        }
    }
}
