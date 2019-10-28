package tw.kgips.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tw.kgips.dto.hello_world.HelloWorldDTO;
import tw.kgips.persistence.dao.HelloWorldDao;
import tw.kgips.persistence.entity.HelloWorldEntity;

@Component
public class HelloWorldService {

	private static final Logger logger = Logger.getLogger(HelloWorldService.class);

	private HelloWorldDao helloDao;

	@Autowired
	public void setHelloDao(HelloWorldDao helloDao) {
		this.helloDao = helloDao;
	}

	public String getDesc() {

		logger.debug("getDesc() is executed!");

		return "Gradle + Spring MVC hello_world World Example";

	}

	public String getTitle(String name) {

		logger.debug("getTitle() is executed! $name : " + name);

		if (StringUtils.isEmpty(name)) {
			return "Hello World";
		} else {
			return "Hello " + name;
		}
	}

	@Transactional
	public void createHello(HelloWorldEntity helloEntity) {
		helloDao.createHello(helloEntity);
	}

	@Transactional
	public HelloWorldDTO getHello(Long sn) {
		return HelloWorldDTO.fromEntity(helloDao.getHello(sn));
	}

}
