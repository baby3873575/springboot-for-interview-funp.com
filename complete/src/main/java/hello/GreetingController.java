package hello;

import java.util.concurrent.atomic.AtomicLong;
import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.json.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.google.common.collect.Lists;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;




@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    @Autowired
    private AdRepository repository;

    @RequestMapping("/greeting")
    public List<Ad> greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	
    	Iterable<Ad> iterator = repository.findAll();
    	List<Ad> list = Lists.newArrayList(iterator);
    	return list;
    }
    
    @RequestMapping("/getAds")
    public List<Ad> getads(@RequestParam(value="name", defaultValue="") String name) {
    	
    	Iterable<Ad> iterator = repository.findByTitleContainingIgnoreCase(name);
    	List<Ad> list = Lists.newArrayList(iterator);
    	return list;
    }
}