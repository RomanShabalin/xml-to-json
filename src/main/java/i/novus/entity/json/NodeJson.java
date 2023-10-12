package i.novus.entity.json;

public class NodeJson {
    private String value;
    private ValueJson nodeC;
    private ValueJson nodeD;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ValueJson getNodeC() {
        return nodeC;
    }

    public void setNodeC(ValueJson nodeC) {
        this.nodeC = nodeC;
    }

    public ValueJson getNodeD() {
        return nodeD;
    }

    public void setNodeD(ValueJson nodeD) {
        this.nodeD = nodeD;
    }
}
