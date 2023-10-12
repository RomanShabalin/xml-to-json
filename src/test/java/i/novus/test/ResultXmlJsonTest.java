package i.novus.test;

import i.novus.entity.ResultXmlJson;
import i.novus.entity.xml.MainXml;
import i.novus.entity.xml.NodeXml;
import i.novus.entity.xml.RootXml;
import org.junit.jupiter.api.Test;

public class ResultXmlJsonTest {
    @Test
    public String testCorrect() {
        MainXml correctMainXml = new MainXml();
        RootXml correctRootXml = new RootXml();

        correctRootXml.setNodeA("Node 1");

        NodeXml nodeB = new NodeXml();
        nodeB.setNodeC("Node 2");
        nodeB.setNodeD("Node 3");
        correctRootXml.setNodeB(nodeB);

        correctRootXml.setNodeE("Node 4");

        correctMainXml.setRoot(correctRootXml);

        ResultXmlJson correctResultXmlJson = new ResultXmlJson(correctMainXml);
        return correctResultXmlJson.calculateResult();
    }

    @Test
    public String testIncorrect() {
        ResultXmlJson incorrectResultXmlJson = new ResultXmlJson(new MainXml());
        return incorrectResultXmlJson.calculateResult();
    }
}
