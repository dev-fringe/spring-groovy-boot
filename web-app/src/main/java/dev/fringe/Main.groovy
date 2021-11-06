package dev.fringe

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

import dev.fringe.config.MainConfig
import dev.fringe.model.MainModel
import dev.fringe.persistence.MainMapper
import dev.fringe.service.MainService

@Configuration
@Import(value = MainConfig.class)
class Main implements InitializingBean {

	@Autowired MainService mainService;

	static void main(String[] args) {
		new AnnotationConfigApplicationContext(Main.class)
	}

	void afterPropertiesSet() throws Exception {
		mainService.save(new MainModel(model: '28', type: 'sdsd'))
	}
}