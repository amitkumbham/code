package com.movie.crud.operations;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
@Path("/movieManage")
public class MovieManagementService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
	
	@POST
	@Path("/addMovie")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addMovie(@FormParam("movieId") String movieId,
            @FormParam("movieName") String movieName,
            @FormParam("movieCast") String movieCast,@FormParam("director") String director) {
		
		List<MovieVO> movies = null;
 
		try{
			MovieVO vo = new MovieVO();
			vo.setDirector(director);
			vo.setMovieCast(movieCast);
			vo.setMovieName(movieName);
			vo.setMovieNumber(movieId);
			InMemeoryCollectionDB dbObj =new InMemeoryCollectionDB();
			dbObj.addMovie(vo);
			movies = dbObj.searchMovie(null);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		return Response.status(200).entity(buildMovieDetails(movies)).build();
 
	}
	
	private String buildMovieDetails(List<MovieVO> vos){
		String responseHTML= "<html><head><b>Movie Details After CRUD operation</b></head><body><table style='border: 1px solid black;'><tr><td>Movie Idr</td><td>Movie Name</td><td>Movie Cast</td><td>Directo</td></tr>";
		for(MovieVO vo :vos){
			responseHTML = responseHTML.concat("<tr><td>").concat(vo.getMovieNumber()).concat("</td>");
			responseHTML = responseHTML.concat("<td>").concat(vo.getMovieName()).concat("</td>");
			responseHTML = responseHTML.concat("<td>").concat(vo.getMovieCast()).concat("</td>");
			responseHTML = responseHTML.concat("<td>").concat(vo.getDirector()).concat("</td></tr>");
		}
		
		responseHTML.concat("</table></body></html>");
		return responseHTML;
	}
	
	@POST
	@Path("/deleteMovie")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteMovie(@FormParam("movieId") String movieId) {
 
		List<MovieVO> movies = null;
		try{
			InMemeoryCollectionDB obj = new InMemeoryCollectionDB();
			obj.deleteMovie(movieId);
			movies = obj.searchMovie(null);
		}catch(Exception ex){
			
		}
		return Response.status(200).entity(buildMovieDetails(movies)).build();
	}
	
	@POST
	@Path("/searchMovie")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response searchMovie(@FormParam("movieId") String movieId) {
 
		InMemeoryCollectionDB obj = new InMemeoryCollectionDB();
		List<MovieVO> vos=obj.searchMovie(movieId);
		return Response.status(200).entity(buildMovieDetails(vos)).build();
 
	}
 
}
