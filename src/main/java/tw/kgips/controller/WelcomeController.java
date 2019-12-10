package tw.kgips.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tw.kgips.service.HelloWorldService;

import java.util.Map;

@Controller
public class WelcomeController {

	private static final Logger logger = Logger.getLogger(WelcomeController.class);
	private final HelloWorldService helloWorldService;

	@Autowired
	public WelcomeController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {

		logger.debug("welcome() is executed!");

		model.put("title", helloWorldService.getTitle(""));
		model.put("message", helloWorldService.getDesc());

		return "welcome";
	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		logger.debug(String.format("hello() is executed - %s {}", name));

		ModelAndView model = new ModelAndView();
		model.setViewName("welcome");

		model.addObject("title", helloWorldService.getTitle(name));
		model.addObject("message", helloWorldService.getDesc());

		return model;

	}

}
