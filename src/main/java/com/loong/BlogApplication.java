package com.loong;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.loong.blog.model.DictItem;
import com.loong.blog.service.IDictItemService;
import com.loong.common.constant.CacheConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Autowired
	IDictItemService dictItemService;

	/**
	 * 解决Jackson导致Long型数据精度丢失问题
	 * @return
	 */
	@Bean("jackson2ObjectMapperBuilderCustomizer")
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		Jackson2ObjectMapperBuilderCustomizer customizer = new Jackson2ObjectMapperBuilderCustomizer() {
			@Override
			public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
				jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance)
						.serializerByType(Long.TYPE, ToStringSerializer.instance);
			}
		};
		return customizer;
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	/**
	 * 初始化加载数据
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		List<DictItem> list = dictItemService.list();
		for (DictItem item : list) {
			HashMap<String, String> dicMap = CacheConstant.dicMap.get(item.getDicCode());
			if(null == dicMap){
				dicMap = new HashMap<>();
				CacheConstant.dicMap.put(item.getDicCode(), dicMap);
			}
			dicMap.put(item.getCode(), item.getName());
			dicMap.put(item.getName(), item.getCode());
		}
	}
}
