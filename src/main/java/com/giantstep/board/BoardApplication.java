package com.giantstep.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;

@EnableJpaAuditing
@SpringBootApplication
public class BoardApplication {
	//test 수정 추가
	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

	/** JPAQueryFactory 빈 등록. */
	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager em) {return new JPAQueryFactory(em);}

}
