import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tw.kgips.dto.hello_world.HelloWorldDTO;
import tw.kgips.persistence.entity.HelloWorldEntity;
import tw.kgips.service.HelloWorldService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/spring-core-config.xml"})
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
