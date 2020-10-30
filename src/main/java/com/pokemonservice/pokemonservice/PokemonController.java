package com.pokemonservice.pokemonservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.css.Counter;

import ch.qos.logback.core.net.SyslogOutputStream;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.MongoClient; //Import MongoDB Database 
import com.mongodb.DB;
import com.mongodb.client.MongoDatabase;  
import com.mongodb.MongoCredential; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/*
************************************************
 * HOW TO RUN SPRING BOOT APPLICATION
 * 1. Open a new terminal 
 * TO BUILD THE JAR FILE: ./mvnw clean package
 * TO RUN: ./mvnw spring-boot:run
 * Added to GitHub
 **********************************************
 */

 /*
************************************************************************
 1.Create a Database and Table using MongoDB
************************************************************************
  */


//Call this in your local host 
@RequestMapping("/pokemon")
@RestController //Handle all HTTP Requests to API and retreives the data 
public class PokemonController{


//Create Logger to show pokemon information in the console
private final Logger log = LoggerFactory.getLogger(PokemonController.class);


//Retreive data and store in a class using class object 
public RestTemplate restTemplate = new RestTemplate();

//Create a Gson object to convert Json data to java object data 
private Gson gson = new Gson();

//Hold value of Http status code when making requests
HttpStatus statuscode = null;

//Create a MondoDB Repository 
@Autowired
private PokemonRepository pokemonrepository;






//This mapping will request the PokemonInfo() method 
@RequestMapping("/name") //Map the Http requests to a handler method getPokemon()
public String getPokemon(@RequestParam(value="name", defaultValue = "pikachu") String name)
{
    PokemonInfo pokemon = new PokemonInfo(name); //Create a PokemonInfo Object

    //Use this url to get Pokemon Data 
    String url =  "https://pokeapi.co/api/v2/pokemon/" + name;

    /* ResponseEntity helps return the whole Http Response into the ResponseEntity Object
    This includes the headers, body, and status*/
    ResponseEntity<String> pokemonInfo = restTemplate.getForEntity(url, String.class);

    //Use Json to get data and store in the Pokemon class using gson
    JsonObject jsonResult = gson.fromJson(pokemonInfo.getBody(), JsonObject.class);

    /*How to get a Josn object that is contained in a Json Array */
    JsonObject types = jsonResult.getAsJsonArray("types").get(0).getAsJsonObject();

    JsonObject abilities  = jsonResult.getAsJsonArray("abilities").get(0).getAsJsonObject();

    


    
      

    /* Do Eexception handling for status codes other than 200(Status Code 200: Successful Request)
    Return the Http Status Code 
     */
   

    /**RETRIEVING DATA */ 
    pokemon.setBaseExperience(jsonResult.get("base_experience").getAsInt());//Retrieve data for base exeprience
    
    pokemon.setWeight(jsonResult.get("weight").getAsInt());//Retreive data for pokemon weight 

    pokemon.setLocationLink(jsonResult.get("location_area_encounters").getAsString());//Retrieve data for the location link

    pokemon.setType(types.getAsJsonObject("type").get("name").getAsString()); //Retreive data for the pokemon type 

    pokemon.setAbilities(abilities.getAsJsonObject("ability").get("name").getAsString()); //Retreive data for the pokemon ability 

    pokemonrepository.save(pokemon); //Save to the database
    

/*
Using String.format() allows us to dislay the data on the local host server page 
THe %s checks if the arguement is a null string and passes the arguement if it is
*/
 return String.format(name.toUpperCase() + ", " + "I CHOOSE YOU!: %s", pokemon.toString());


   


    
}







    
}


