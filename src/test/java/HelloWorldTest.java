import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tw.kgips.service.HelloWorldService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/spring-core-config.xml"})
public class HelloWorldTest {

	@Autowired
	HelloWorldService helloWorldService;

	@Test
	public void testMarketInfoServiceHelloWorld() {
		assertThat(helloWorldService.getTitle("Samson"), containsString("Samson"));
	}

}
