package com.venkat.springobservability;


import com.venkat.springobservability.service.JsonPlaceholderService;
import com.venkat.springobservability.vo.Post;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class SpringObservabilityApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringObservabilityApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringObservabilityApplication.class, args);
	}

	@Bean
	@Observed(name="posts.load-all-posts",contextualName = "post.find-all")
	CommandLineRunner commandLineRunner(JsonPlaceholderService service){
		return args -> {
			List<Post> posts = jsonPlaceholderService().findAll();
			log.info("All posts: {}" + posts.size());
		};
	}

	/*@Bean
	CommandLineRunner commandLineRunner(JsonPlaceholderService service, ObservationRegistry registry){
		return args -> {
			Observation.createNotStarted("posts.load-all-posts", registry)
					.lowCardinalityKeyValue("author", "Venkatram R. Veerareddy")
					.contextualName("postService.findAll")
					.observe(() ->{
						List<Post> posts = jsonPlaceholderService().findAll();
						log.info("All posts: {}" + posts.size());
					});
		};
	}*/

	@Bean
	JsonPlaceholderService jsonPlaceholderService(){
		//to call another rest service using RestClient
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(JsonPlaceholderService.class);
	}
}
