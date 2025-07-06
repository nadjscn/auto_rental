package com.xzit.rental.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
/**
 * 实现mybatis plus 自动填充功能
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now,
                LocalDateTime.class); // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now,
                LocalDateTime.class); // 起始版本 3.3.3(推荐)
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now,
                LocalDateTime.class); // 起始版本 3.3.3(推荐)
    }
}
