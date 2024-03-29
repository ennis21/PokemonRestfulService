package com.pokemonservice.pokemonservice;

import org.springframework.data.mongodb.repository.MongoRepository; 

//The interface handles the necessary functions used to uttilize the MongoDB Repository
public interface PokemonRepository extends MongoRepository<PokemonInfo,String> { 
    
    public PokemonInfo findbyName(String name); //Check the respository for all the pokemon with the givne name 
    public PokemonInfo findbyPokemonType(String type); //Check the repository for all the pokemon with the same pokemon type 

}
