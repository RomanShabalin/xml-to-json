package i.novus;

import i.novus.test.ResultXmlJsonTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XmlToJsonApplicationTests {
    private final ResultXmlJsonTest resultXmlJsonTest = new ResultXmlJsonTest();

    @Test
    public void contextLoads() {
        assertThat(resultXmlJsonTest).isNotNull();
    }

    @Test
    public void checkCorrect() {
        assertThat(resultXmlJsonTest.testCorrect()).isEqualTo("{\"root\":{\"value\":\"10\",\"nodeA\":{\"value\":\"1\"},\"nodeB\":{\"value\":\"5\",\"nodeC\":{\"value\":\"2\"},\"nodeD\":{\"value\":\"3\"}},\"nodeE\":{\"value\":\"4\"}}}");
    }

    @Test
    public void checkIncorrect() {
        assertThat(resultXmlJsonTest.testIncorrect()).isEmpty();
    }
}
