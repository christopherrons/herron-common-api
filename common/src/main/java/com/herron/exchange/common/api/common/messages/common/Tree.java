package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public record Tree(@JsonProperty("rootNode") TreeNode rootNode) {

    public static class TreeNode {

        private final String id;
        private final String name;
        private List<TreeNode> children;

        @JsonCreator
        public TreeNode(
                @JsonProperty("id") String id,
                @JsonProperty("name") String name,
                @JsonProperty("children") List<TreeNode> children) {
            this.id = id;
            this.name = name;
            this.children = children != null ? children : new ArrayList<>();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<TreeNode> getChildren() {
            return children;
        }

        public void setChildren(List<TreeNode> children) {
            this.children = children;
        }

        public void addChild(TreeNode childNode) {
            if (children == null) {
                children = new ArrayList<>();
            }
            children.add(childNode);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TreeNode treeNode)) return false;
            return Objects.equals(id, treeNode.id) && Objects.equals(name, treeNode.name) && Objects.equals(children, treeNode.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, children);
        }
    }
}
