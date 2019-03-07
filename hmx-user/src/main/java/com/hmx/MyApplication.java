package com.hmx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(value = {"com.hmx.*.dao"})
public class MyApplication {

	public static void main(String[] args){
		SpringApplication.run(MyApplication.class, args);
	}
}