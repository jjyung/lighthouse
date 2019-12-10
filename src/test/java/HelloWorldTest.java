import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tw.kgips.config.AppConfig;
import tw.kgips.dto.hello_world.HelloWorldDTO;
import tw.kgips.persistence.entity.HelloWorldEntity;
import tw.kgips.service.HelloWorldService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfig.class})
public class HelloWorldTest {

	@Autowired
	HelloWorldService helloWorldService;

	@Test
	public void testHelloWorldService() {
		assertThat(helloWorldService.getTitle("Samson"), containsString("Samson"));
	}

	@Test
	public void testDaoCreateRead() {
		HelloWorldEntity create = new HelloWorldEntity();
		create.setName("test");

		helloWorldService.createHello(create);

		HelloWorldDTO read = helloWorldService.getHello(create.getSn());

		assertThat(read.getName(), equalTo(create.getName()));
	}

}
