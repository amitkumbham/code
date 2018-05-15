package com.movie.crud.testops;

import com.movie.crud.operations.InMemeoryCollectionDB;
import com.movie.crud.operations.MovieVO;

public class TestMovieManagementService {
	
	public static void main(String a[]){
		
		//Add movie
		MovieVO movie = new MovieVO();
		InMemeoryCollectionDB movieObj = new InMemeoryCollectionDB();
		movie.setMovieNumber("1");
		movie.setMovieName("Name1");
		movie.setMovieCast("Cast1");
		movie.setDirector("Dir1");
		movieObj.addMovie(movie);
		
		movieObj = new InMemeoryCollectionDB();
		movie.setMovieNumber("2");
		movie.setMovieName("Name2");
		movie.setMovieCast("Cast2");
		movie.setDirector("Dir2");
		movieObj.addMovie(movie);
		
		movieObj = new InMemeoryCollectionDB();
		movie.setMovieNumber("3");
		movie.setMovieName("Name3");
		movie.setMovieCast("Cast3");
		movie.setDirector("Dir3");
		movieObj.addMovie(movie);
		
		//Search with id
		System.out.println(movieObj.searchMovie("1"));
		
		//Search All
		System.out.println(movieObj.searchMovie(null));
		
		//Delete Movie
		movieObj.deleteMovie("1");
		
		//Search post delete
		
		System.out.println(movieObj.searchMovie("1"));
		
	}

}
