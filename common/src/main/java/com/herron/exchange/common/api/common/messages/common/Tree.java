package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


public record Tree(TreeNode rootNode) {

    public record TreeNode(@JsonProperty("id") String id,
                           @JsonProperty("name") String name,
                           @JsonProperty("children") List<TreeNode> children) {

        @JsonCreator
        public TreeNode(@JsonProperty("id") String id) {
            this(id, id, new ArrayList<>());
        }

        public void addChild(TreeNode childNode) {
            children.add(childNode);
        }
    }
}
