package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
		excludeFilters= @ComponentScan.Filter(type= FilterType.ANNOTATION, classes= Configuration.class)
		// excludeFilters 는 기존코드에 영향을 안받기위해 사용, @Configuration 되 있는것들이 많음
	)
public class AutoAppConfig {

}
