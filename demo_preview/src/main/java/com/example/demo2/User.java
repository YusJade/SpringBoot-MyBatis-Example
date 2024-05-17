// 注意 这里的包名 com.example.demo2 因人而异
// com.{你的组名}.{你的工程名称}
package com.example.demo2;

import lombok.Data;

// 这里使用了 Lombok ，它可以自动为各个属性添加 Getter/Setter 方法
@Data
public class User {
    private int id;
    private String name;

/*
    如果你不想使用 Lombok , 按下 alt + ins
    选择生成 Getter Setter， 勾选所有属性，然后确定即可
    接着 IDEA 会为你 生成以下代码
*/

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

}
