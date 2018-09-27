package com.michaelssss.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class BusinessCodeGenerator {
    private RecordEntityRepository recordEntityRepository;

    @Autowired
    public BusinessCodeGenerator(RecordEntityRepository recordEntityRepository) {
        this.recordEntityRepository = recordEntityRepository;
    }

    public String getSequence(Class<?> clazz, String prefix) {
        if (StringUtils.isEmpty(prefix)) {
            prefix = "anonymous";
        }
        //1. 找一下，table里面有没有
        //2. 若无则新建
        //3. 若有则更新并保存
        RecordEntity sample = new RecordEntity();
        RecordEntity actual;
        sample.setPrefix(prefix);
        sample.setClazzName(clazz.getSimpleName());
        if (recordExist(sample)) {
            actual = recordEntityRepository.findOne(Example.of(sample)).get();
            actual.setSequence(actual.getSequence() + 1L);
        } else {
            actual = new RecordEntity();
            actual.setPrefix(prefix);
            actual.setClazzName(clazz.getSimpleName());
            actual.setSequence(1L);
            actual = recordEntityRepository.saveAndFlush(actual);
        }
        return actual.getPrefix() + "-" + actual.getSequence();
    }

    private boolean recordExist(RecordEntity sample) {
        return recordEntityRepository.count(Example.of(sample)) != 0;
    }
}

