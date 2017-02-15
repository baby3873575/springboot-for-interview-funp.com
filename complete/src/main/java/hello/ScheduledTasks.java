package hello;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    
    @Autowired
    private AdRepository repository;
	
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.getForEntity("https://beta-ssp.tenmax.io/supply/mobile/native/rmax-ad?rmaxSpaceId=55ba76bca772421f&dpid=bd4b9b7903cf40ce&v=1", String.class);

	           
		JSONObject res = new JSONObject(response.getBody().toString());
		JSONObject obj = res.getJSONObject("native");
//		Ad(String  title, String description,String click_url,String [] impression_links)
		
		
		
		
		JSONArray impress_link = obj.getJSONArray("impressionEvent");
		String click_ur=obj.getJSONObject("link").getString("url");		
		
		ArrayList<String> ils = new ArrayList<String>();
		for (int i = 0; i < impress_link.length(); ++i) {
			ils.add((String)impress_link.getString(i) );		    
		}		
		String[] ilsArr = new String[ils.size()];
		ilsArr = ils.toArray(ilsArr);
		
		
		String title="";
		String description="";
		
		JSONArray assets_sets = obj.getJSONArray("assets");
		for (int i = 0; i < assets_sets.length(); ++i) {
			JSONObject ass = assets_sets.getJSONObject(i);	
			if ( ass.has("img") ){
				
			}else if(ass.has("title")){
				title = ass.getJSONObject("title").getString("text");
			}else if(ass.has("data")){				
				description= ass.getJSONObject("data").getString("value");
			}
		}	
		
		
		
		repository.save(new Ad(title,description,click_ur,ilsArr));
		
		
		
//		log.info("Customers found with findAll():");
//		log.info("-------------------------------");
//		for (Ad ad : repository.findAll()) {
//			log.info(ad.toString());
//		}

    }
    
    
//    
//    @Entity(name = "asset") 
//    class Asset{
//    	
//    }
    
    
    
//    
//    @Entity(name = "CUSTOMER") 
//    class Customer {
//    	
//           @Id //signifies the primary key  
//           @Column(name = "CUST_ID", nullable = false)
//           @GeneratedValue(strategy = GenerationType.AUTO)
//           private long custId;
//           
//           @Column(name = "FIRST_NAME", length = 50)
//           private String firstName;
//           
//           @Column(name = "LAST_NAME", nullable = false,length = 50)
//           private String lastName;
//           
//           @Column(name = "STREET")
//           private String street;
//           @OneToMany(mappedBy="customer",targetEntity=Order.class,fetch=FetchType.EAGER)
//           private Collection orders;       
//
//           // The other attributes and getters and setters goes here
//    }
//    
//    @Entity(name = "ORDERS") 
//    class Order {
//           
//           @Id //signifies the primary key
//           @Column(name = "ORDER_ID", nullable = false)
//           @GeneratedValue(strategy = GenerationType.AUTO)
//           private long orderId;
//           
//    
//         
//          
//    }


    
}
