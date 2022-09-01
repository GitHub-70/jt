package com.jt.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatisplus
 * 公共字段填充处理器类：
 *
 * BasePojo已经处理
 */
@Component  //将对象交给spring容器管理
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date(); //保证时间一致
        // 如果存在 "created" 这个字段，就更新这个字段
        if (metaObject.hasGetter("created")){
            this.setInsertFieldValByName("created", date, metaObject);
        }
        if (metaObject.hasGetter("updated")){
            this.setInsertFieldValByName("updated", date, metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setUpdateFieldValByName("updated", new Date(), metaObject);

    }
}
