package com.giantstep.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoardApplication {
	//test 수정 추가
	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}
