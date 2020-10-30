package com.pokemonservice.pokemonservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mongodb.MongoClient; //Import MongoDB Database 
import com.mongodb.DB;
import com.mongodb.client.MongoDatabase;

import org.springframework.data.annotation.Id;


//Any properties not bound in the type that are passed are ignored 
@JsonIgnoreProperties(ignoreUnknown = true)

public class PokemonInfo{

@Id //Annotation related to MongoDB
private String Id = "";
private String name = ""; //Pokemon Name 
private String type = ""; //Pokemon Type 
private String abilities = ""; //Pokemon Species 
private int base_experience = 0; //The base experience gained for defeating this Pokémon.
private int weight = 0; //The weight of this Pokémon in hectograms.
private String location_area_encounters = ""; //A link to a list of location areas, as well as encounter details pertaining to specific versions.
private static int numberofpokemon = 0; //Keep track of the number of pokemon added

//Default Contructor Arg
public PokemonInfo()
{

}

public PokemonInfo(String name)
{
    this.name = name;
    numberofpokemon++; //Increment the count of the number of pokemon searched 
}

//Add stored data into a database 
public void addToDatabase()
{

}

public void setName(String name)
{  
    this.name = name;
}

public void setType(String type)
{
    
    this.type = type;
}

public void setLocationLink(String location_area_encounters)
{
    this.location_area_encounters = location_area_encounters;
}

public void setAbilities(String abilities)
{
    this.abilities = abilities;
}

public void setWeight(int weight)
{
    this.weight = weight;
}
public void setBaseExperience(int base_experience)
{
    this.base_experience = base_experience;
}

//Return the Pokemon Type 
public String getType()
{
    return this.type;
}

//Return the name of the pokemon 
public String getName()
{
    
    return this.name;
}

//Return the pokemon weight 
public int getWeight()
{
    return this.weight;
    
}

//Return the pokemon location link
public String getLocationLink()
{
    return this.location_area_encounters;
}

//Return the base experience of a pokemon
public int getBaseExperience()
{
    return this.base_experience;
}

//Return the pokemon species 
public String getAbilities()
{
    return this.abilities;
}



//Return the data in a String representation
public String toString()
{
    return "Pokemon Name: " + getName() + "; " + "Weight: " + getWeight()
    + " hectograms" + "; " + "Base Experience: " + getBaseExperience() + "; " + "Location Link " + getLocationLink() +
    "; " + "Pokemon Type: " + getType() + "; " + "Pokemon Ability: " + getAbilities() + "; " + "Number of Pokemon Searched: "  + numberofpokemon;
}

}