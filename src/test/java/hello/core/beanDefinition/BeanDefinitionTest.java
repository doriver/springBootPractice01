package hello.core.beanDefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import hello.core.AppConfig;

public class BeanDefinitionTest {
//  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
  GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml"); // 이걸로 하면 4개만 나옴 , 위에껀 더 나옴
  
  @Test
  @DisplayName("빈 설정 메타정보 확인")
  void findBeanDefinition() {
	  String[] beanNames = ac.getBeanDefinitionNames();
	  for(String beanName : beanNames) {
		  BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);
		  
		  if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				System.out.println("ROLE_APPLICATION / Name = " + beanName + " / beanDefinition = " + beanDefinition);
			} else {
				System.out.println( beanDefinition.getRole() + " / Name = " + beanName + " / beanDefinition = " + beanDefinition);
			}
	  }
  }
}
