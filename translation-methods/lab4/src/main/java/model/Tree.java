package model;

import java.util.*;

public class Tree {
    private static final String GRAPH_NODE_PATTERN = "\tNode%d [label=\"%s\"];";
    private static final String GRAPH_RELATION_PATTERN = "\tNode%d -> Node%d;";

    private final Map<String, Tree> children = new LinkedHashMap<>();
    private final String name;
    private long nodeCounter = 0;


    public Tree(String name) {
        this.name = name;
    }

    public Tree(String name, Tree... children) {
        this(name);
        for (Tree child : children) {
            this.children.put(child.name, child);
        }
    }

    public String toDotGraph() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph tree {").append(System.lineSeparator());
        dfs(this, sb, -1);
        sb.append("}");
        return sb.toString();
    }

    private void dfs(Tree tree, StringBuilder sb, long parentCount) {
        if (!tree.name.equals("eps")) {
            sb.append(GRAPH_NODE_PATTERN.formatted(nodeCounter, tree.name)).append(System.lineSeparator());
            long curCount = nodeCounter;
            for (Tree child : tree.children.values()) {
                nodeCounter++;
                dfs(child, sb, curCount);
            }
            if (parentCount != -1) {
                sb.append(GRAPH_RELATION_PATTERN.formatted(parentCount, curCount)).append(System.lineSeparator());
            }
        }
    }

    public void addChild(Tree child) {
        children.put(child.name, child);
    }

    public Map<String, Tree> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }
}
