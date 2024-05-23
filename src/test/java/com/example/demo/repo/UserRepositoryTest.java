package com.example.demo.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

//@SpringBootTest // 이거 없으면 안됨 , 다른 방법도 있을듯?

@DataJpaTest// 이거 하나만 있으면 안됨
//@ActiveProfiles("local") 이거 없어도 됐음
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
/* @AutoConfigureTestDataBase : 데이터 소스를 어떤 걸로 사용할지에 대한 설정
Replace.Any : 기본적으로 내장된 데이터소스를 사용
Replace.NONE : @ActiveProfiles 기준으로 프로파일이 설정됨
 */
@Rollback(false)
public class UserRepositoryTest {
	@Autowired 
	UserRepository userRepository;
	
	@Test
	public void findById() {
		// given
		Long id = 1L;
		
		// when
		Optional<User> ou = userRepository.findById(id);
		
		// then
		assertEquals(ou.get().getEmail(), "naver.com");
	}
	
	@Test
	public void findAll() {
		List<User> li = userRepository.findAll();
		System.out.println(li);
	}

	@Test
	public void save() {
		User us = new User();
		us.setUsername("asdad");
		us.setEmail("gooom");
		
		User inserted = userRepository.save(us);
		
		Optional<User> ou = userRepository.findById(inserted.getId());
		
		System.out.println(inserted);
		System.out.println(ou);
		
		assertThat(inserted).isEqualTo(ou.get());
	}
	@Test
	public void ss() {
		
	}
	@Test
	public void sa() {
		
	}
	@Test
	public void se() {
		
	}
}
