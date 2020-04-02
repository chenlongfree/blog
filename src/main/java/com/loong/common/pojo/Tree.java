package com.loong.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tree {
    private String name;
    private String value;
    private String parent;

    private List<Tree> children;

    public Tree(String name, String value, String parent){
        this.name = name;
        this.value = value;
        this.parent = parent;
    }
}
