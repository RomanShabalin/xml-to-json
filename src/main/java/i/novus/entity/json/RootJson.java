package i.novus.entity.json;

public class RootJson {
    private String value;
    private ValueJson nodeA;
    private NodeJson nodeB;
    private ValueJson nodeE;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setNodeA(ValueJson nodeA) {
        this.nodeA = nodeA;
    }

    public void setNodeB(NodeJson nodeB) {
        this.nodeB = nodeB;
    }

    public void setNodeE(ValueJson nodeE) {
        this.nodeE = nodeE;
    }
}
