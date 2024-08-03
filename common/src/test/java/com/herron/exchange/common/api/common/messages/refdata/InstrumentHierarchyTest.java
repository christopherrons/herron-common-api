package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.common.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InstrumentHierarchyTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var rootNode = new Tree.TreeNode("root");
        var child1 = new Tree.TreeNode("child-1");
        var child2 = new Tree.TreeNode("child-2");
        var child11 = new Tree.TreeNode("child-11");
        var child21 = new Tree.TreeNode("child-21");
        rootNode.addChild(child1);
        rootNode.addChild(child2);
        child1.addChild(child11);
        child2.addChild(child21);
        var object = ImmutableInstrumentHierarchy.builder()
                .timeStamp(Timestamp.now())
                .instrumentTree(new Tree(rootNode))
                .build();

        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
        assertEquals(object, messageFactory.deserializeMessage(value));
    }
}