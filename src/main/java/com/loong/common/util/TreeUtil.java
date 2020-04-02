package com.loong.common.util;

import com.loong.common.pojo.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    public static List<Tree> getTreeData(List<Tree> trees){
        List<Tree> modelTrees=new ArrayList<>();
        for(Tree tree:trees) {
            if(tree.getParent()==null) {
                Tree modelTree=new Tree(tree.getName(), tree.getValue(), tree.getParent());
                modelTree.setChildren(getChildrenNode(modelTree.getValue(), trees));
                modelTrees.add(modelTree);
            }
        }

        return  modelTrees;
    }

    public static List<Tree> getChildrenNode(String value, List<Tree> trees) {
        List<Tree> modelTrees=new ArrayList<>();
        for (Tree tree:trees) {
            if(tree.getParent()==null){
                continue;
            }
            if(value.equals(tree.getParent())){
                Tree modelTree=new Tree(tree.getName(), tree.getValue(), tree.getParent());
                modelTree.setChildren(getChildrenNode(tree.getValue(), trees));
                modelTrees.add(modelTree);
            }
        }
        return modelTrees;
    }
}
