package i.novus.entity;

import com.google.gson.Gson;
import i.novus.entity.json.MainJson;
import i.novus.entity.json.NodeJson;
import i.novus.entity.json.RootJson;
import i.novus.entity.json.ValueJson;
import i.novus.entity.xml.MainXml;
import i.novus.entity.xml.RootXml;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultXmlJson {
    private MainXml main;

    public ResultXmlJson(MainXml main) {
        this.main = main;
    }

    public String calculateResult() {
        String returnStr = "";
        RootXml root = main.getRoot();

        if (root != null) {
            MainJson mainJson = new MainJson();

            RootJson rootJson = new RootJson();

            ValueJson valueJsonA = new ValueJson();
            String valueA = this.parseStringAndGetValue(root.getNodeA());
            valueJsonA.setValue(valueA);
            rootJson.setNodeA(valueJsonA);

            ValueJson valueJsonC = new ValueJson();
            String valueC = this.parseStringAndGetValue(root.getNodeB().getNodeC());
            valueJsonC.setValue(valueC);

            ValueJson valueJsonD = new ValueJson();
            String valueD = this.parseStringAndGetValue(root.getNodeB().getNodeD());
            valueJsonD.setValue(valueD);

            int valueB = Integer.parseInt(valueC) + Integer.parseInt(valueD);
            NodeJson nodeB = new NodeJson();
            nodeB.setNodeC(valueJsonC);
            nodeB.setNodeD(valueJsonD);
            nodeB.setValue(Integer.toString(valueB));

            rootJson.setNodeB(nodeB);

            ValueJson valueJsonE = new ValueJson();
            String valueE = this.parseStringAndGetValue(root.getNodeE());
            valueJsonE.setValue(valueE);
            rootJson.setNodeE(valueJsonE);

            int rootValue = Integer.parseInt(valueA) + valueB + Integer.parseInt(valueE);
            rootJson.setValue(String.valueOf(rootValue));

            mainJson.setRoot(rootJson);

            Gson gson = new Gson();
            returnStr = gson.toJson(mainJson, MainJson.class);
        }

        return returnStr;
    }

    private String parseStringAndGetValue(String str) {
        Pattern pattern = Pattern.compile("(?<![\\.\\d])\\d+(?![\\.\\d])");
        Matcher matcher = pattern.matcher(str);
        int sum = 0;

        if (matcher.find()) {
            sum += Integer.parseInt(matcher.group(0));
        }

        return String.valueOf(sum);
    }
}
