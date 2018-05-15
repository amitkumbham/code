package com.movie.crud.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemeoryCollectionDB {
	
	public static Map<String, MovieVO> movieIdMap = new HashMap<>();
	
	public synchronized void addMovie(MovieVO movieVO){
		movieIdMap.put(movieVO.getMovieNumber().toUpperCase(), movieVO);
	}
	
	public synchronized void deleteMovie(String movieNumber){
		movieIdMap.remove(movieNumber.toUpperCase());
	}
	
	public List<MovieVO> searchMovie(String movieNumber){
		List<MovieVO> matches = new ArrayList<>();
		for(String movieID : movieIdMap.keySet()){
			if(movieNumber!=null){
				if(movieID.contains(movieNumber.toUpperCase())){
					matches.add(movieIdMap.get(movieID));
				}
			}else{
				matches.add(movieIdMap.get(movieID));
			}
		}
		
		return matches;
	}

}
