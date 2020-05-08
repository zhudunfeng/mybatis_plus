package com.atguigu.mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    //@TableId(type = IdType.ID_WORKER)//默认主键策略，使用雪花算法
    //@TableId(type = IdType.ID_WORKER_STR)//在项目中会进行使用，使用雪花算法的字符串类型的主键策略，*`解决js无法处理多余（超过）16个字符长度的数值的问题，会出现丢失精度
    //@TableId(type = IdType.AUTO)//主键自增长
    //@TableId(type = IdType.NONE)无策略
    //@TableId(type = IdType.INPUT)
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //用户信息被查看的次数
    private Integer viewCount;

    //乐观锁版本号
    @Version
    private Integer version;

    //逻辑删除字段
    @TableLogic
    private Integer deleted;

}
