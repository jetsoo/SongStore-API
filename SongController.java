package com.example.mySpringProject.controller;

import entities.Song;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import repository.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;



@RestController
@RequestMapping("/songs/api")
public class SongController {
	private final SongService songService;
		
		@Autowired
		public SongController(SongService songService) {
			this.songService = songService;
	}
	
	//BASEPATH 
	@GetMapping
	public Iterable<Song> getAll(){
		return this.songService.getAllSongs();
	}
	
	//BETWEEN RELEASEYEARS 
	@GetMapping("/byreleaseyear")
	public List<Song> getByBetweenReleaseYears(@RequestParam Integer minYear, @RequestParam Integer maxYear){
		return this.songService.getByBetweenReleaseYears(minYear, maxYear);
	}

	
	//BY ID 
	@GetMapping("/{id}")
	public Song getSongById(@PathVariable("id") Integer id) {
		return this.songService.getById(id);
	}
	
	//BY LIKED IS TRUE 
	@GetMapping("/byliked")
	public List<Song> getByLiked(){
		return this.songService.getByLikedTrue();
	}
	
	//BY SONGS WITH MORE THAN X VIEWS 
	@GetMapping("/viewmorethan")
	public List<Song> getByMinViews(@RequestParam(name="minviews")Integer views){
		return this.songService.getByViewsMoreThan(views);
	}
	//GET PRICE 
	@GetMapping("/price/{id}")
	public Float getPriceById(@PathVariable("id") Integer id) {
		return this.songService.getPrice(id);
	}
	//GET PRICE WITH -X % GIFTCARD 
	@GetMapping("/pricewithreduction/{id}/{reduction}")
	public Float getPriceWithReduction(@PathVariable("id") Integer id,@PathVariable("reduction") Float reduction) {
		return this.songService.getPriceWithReduction(id,reduction);
	}
	//NEXT SONG BY ID 
	@GetMapping("/nextid/{id}")
	public Song getByNextId(@PathVariable("id") Integer id) {
		return this.songService.getNextSongById(id);
	}
	
	//ADDING NEW SONG 
	@PostMapping
	public Song addSong(@RequestBody Song newSong) {
		return this.songService.addNewSong(newSong);
	}
	
	//DELETING SONG BY ID 
	@DeleteMapping("/{id}")
	public void deleteSong(@PathVariable("id") Integer id) {
		this.songService.deleteSongById(id);
	}
	
	//UPDATE SONG 
	@PutMapping("/{id}")
	public Song updateSong(@PathVariable("id") Integer id, @RequestBody Song song) {
		return this.songService.updateSongById(id, song);
	}
	
	//UPDATE(SWAP) LIKED STATUS BY ID 
	@PutMapping("/{id}")
	public Song updateLiked(@PathVariable("id") Integer id) {
		return this.songService.updateLiked(id);
	}
	//UPDATE ALIVE TO DEAD 
	@PutMapping("/{id}")
	public Song updateAliveToDead(@PathVariable("id")Integer id) {
		return this.songService.updateAliveToDead(id);
	}
	
	//UPDATE VIEWS INCREMENT BY ID 
	@PutMapping("/{id}")
	public void incrementViews(@PathVariable("id") Integer id) {
		this.songService.incrementViewsById(id);
	}
	
	//UPDATE VIEWS DECREMENT BY ID 
	@PutMapping("/{id}")
	public void decrementViews(@PathVariable("id") Integer id) {
		this.songService.decrementViewsById(id);
	}
	
	//SEARCH V4 
	
	@GetMapping("/searchall")
	public List<Song> searchAll(@RequestParam(required=false) String songName,
			@RequestParam(required=false) String artistName,
			@RequestParam(required=false) Integer duration,
			@RequestParam(required=false) Integer views,
			@RequestParam(required=false) Boolean liked){
		return this.songService.searchAll(songName, artistName, duration, views, liked);
	}
	
	
}