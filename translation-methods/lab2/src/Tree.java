import java.util.Arrays;
import java.util.List;

public class Tree {
    private static final String GRAPH_NODE_PATTERN = "\tNode%d [label=\"%s\"];";
    private static final String GRAPH_RELATION_PATTERN = "\tNode%d -> Node%d;";

    private final String node;
    private List<Tree> children;
    private long nodeCounter = 0;

    public Tree(String node, Tree... children) {
        this.node = node;
        this.children = Arrays.asList(children);
    }

    public Tree(String node) {
        this.node = node;
    }

    public String toDotGraph() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph tree {").append(System.lineSeparator());
        dfs(this, sb, -1);
        sb.append("}");
        return sb.toString();
    }

    private void dfs(Tree tree, StringBuilder sb, long parentCount) {
        if (!tree.node.equals("eps")) {
            sb.append(GRAPH_NODE_PATTERN.formatted(nodeCounter, tree.node)).append(System.lineSeparator());
            long curCount = nodeCounter;
            if (tree.children != null) {
                for (Tree child : tree.children) {
                    nodeCounter++;
                    dfs(child, sb, curCount);
                }
            }
            if (parentCount != -1) {
                sb.append(GRAPH_RELATION_PATTERN.formatted(parentCount, curCount)).append(System.lineSeparator());
            }
        }
    }
}
