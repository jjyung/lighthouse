package tw.kgips.service;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class HelloWorldService {

//	private static final Logger logger = LoggerFactory.getLogger(HelloWorldService.class);

	public String getDesc() {

//		logger.debug("getDesc() is executed!");

		return "Gradle + Spring MVC Hello World Example";

	}

	public String getTitle(String name) {

//		logger.debug("getTitle() is executed! $name : {}", name);

		if (StringUtils.isEmpty(name)) {
			return "Hello World";
		} else {
			return "Hello " + name;
		}

	}

}
