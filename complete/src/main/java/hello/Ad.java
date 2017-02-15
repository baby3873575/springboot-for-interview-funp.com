package hello;

import java.util.List;
import java.util.*;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;


@Entity 
public class Ad {
	
	@Id //signifies the primary key        
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column( length = 100000 )
	private String title;
	
	@Column( length = 100000 )
	private String description;
	
	@Column( length = 100000 )
	private String click_url;
	
	@Column( length = 100000 )
	public String [] impression_links;
	    	
	

    @Override
    public String toString() {
        return this.title+"  "+ Arrays.toString(this.impression_links)+"  "+ this.description+"  "+ this.click_url;
    }

    
//	public Asset[] assets;
    protected Ad(){};
    public Ad(String  title, String description,String click_url,String [] impression_links){
	   this.title=title;
	   this.description=description;
	   this.click_url=click_url;
	   this.impression_links=impression_links;	   
	}

}