package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter // getter, setter 메소드를 자동으로 annotation processing 으로 만들어줌
@ToString
public class HelloLombok {

	private String name;
	private int age;
	
	public static void main(String[] args) {
		HelloLombok lombok = new HelloLombok();
		lombok.setName("asdf");
		System.out.println(lombok); // HelloLombok(name=asdf, age=0)

	}

}
