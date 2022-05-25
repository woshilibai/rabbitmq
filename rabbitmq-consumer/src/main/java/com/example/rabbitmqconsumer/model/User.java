package com.example.rabbitmqconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: todo
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/24 11:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String uid;
    private Integer age;
    private String name;
}
