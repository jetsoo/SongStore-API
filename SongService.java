package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import entities.Song;

@Service
public class SongService {

	private final SongRepository songRepository;
	
	@Autowired
	public SongService(SongRepository songRepository) {
		this.songRepository=songRepository;
	}
	
	//BASEPATH
	public Iterable<Song> getAllSongs(){
		return this.songRepository.findAll();
	}
	
	public Song getById(Integer id) {
		Optional <Song> songByIdOptional = this.songRepository.findById(id);
		if(!songByIdOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id does not exist");
		}
		Song songById = songByIdOptional.get();
		return songById;
	}
	
	public void incrementViewsById(Integer id) {
		Optional <Song> songToIncrementOptional = this.songRepository.findById(id);
		if(!songToIncrementOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id does not exist");
		}
		Song songToIncrement = songToIncrementOptional.get();
		songToIncrement.setViews(songToIncrement.getViews() + 1);
		this.songRepository.save(songToIncrement);
	}
	
	public void decrementViewsById(Integer id) {
		Optional <Song> songToIncrementOptional = this.songRepository.findById(id);
		if(!songToIncrementOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id does not exist");
		}
		Song songToIncrement = songToIncrementOptional.get();
		songToIncrement.setViews(songToIncrement.getViews() - 1);
		this.songRepository.save(songToIncrement);
	}
	
	public Song updateLiked(Integer id) {
		Optional <Song> songToUpdateOptional = this.songRepository.findById(id);
		if(!songToUpdateOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id does not exist");
		}
		Song songToUpdate = songToUpdateOptional.get();
		if(songToUpdate.getLiked()) {
			songToUpdate.setLiked(false);
		}
		else {
			songToUpdate.setLiked(true);
		}
		return this.songRepository.save(songToUpdate);
	}
	
	public Song updateAliveToDead(Integer id) {
		Optional<Song> songToUpdateOptional = this.songRepository.findById(id);
		if(!songToUpdateOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id does not exist");
		}
		Song songToUpdate = songToUpdateOptional.get();
		songToUpdate.setAlive(false);
		return this.songRepository.save(songToUpdate);
	}
	
	public List<Song> getByViewsMoreThan(Integer minViews){
		return this.songRepository.findByViewsGreaterThan(minViews);
	}
	
	public List<Song> getByBetweenReleaseYears(Integer minYear,Integer maxYear){
		return this.songRepository.findByReleaseYearBetween(minYear, maxYear);
	}
	
	public Float getPrice(Integer id) {
		Optional<Song> songToGetPriceOptional = this.songRepository.findById(id);
		if(!songToGetPriceOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id does not exist");
		}
		Song songToGetPrice = songToGetPriceOptional.get();
		return songToGetPrice.getPrice();
	}
	
	public Float getPriceWithReduction(Integer id,Float reduction) {
		Optional<Song> songToGetPriceOptional = this.songRepository.findById(id);
		if(!songToGetPriceOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id does not exist");
		}
		Song songToGetPrice = songToGetPriceOptional.get();
		return ((songToGetPrice.getPrice()/100) * (100 - reduction));
	}
	
	public Song getNextSongById(Integer id) {
		Optional<Song> songNextIdOptional = this.songRepository.findById(id +1);
		if(!songNextIdOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id does not exist");
		}
		Song songNextId = songNextIdOptional.get();
		return songNextId;
		
	}
	
	public List<Song> getByLikedTrue(){
		return this.songRepository.findByLiked(true);
	}
	
	public Song addNewSong(Song newSong) {
		this.songRepository.save(newSong);
		return newSong;
	}
	
	public void deleteSongById(Integer id) {
		Optional<Song> songToDeleteOptional = this.songRepository.findById(id);
		if(!songToDeleteOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id does not exist");
		}
		Song songToDelete = songToDeleteOptional.get();
		this.songRepository.delete(songToDelete);
	}
	
	public Song updateSongById(Integer id, Song songNewVersion) {
		Optional <Song> songToUpdateOptional = this.songRepository.findById(id);
		if(!songToUpdateOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id does not exist");
		}
		Song songToUpdate = songToUpdateOptional.get();
		songToUpdate = songNewVersion;
		return this.songRepository.save(songToUpdate);
	}
	
	//SEARCH V4
	
	public List<Song> searchAll(String songName,String artistName,Integer duration,Integer views,Boolean liked){
		
		
		
		if(songName != null) {
			if(artistName != null) {
					if(duration != null && views != null && liked != null) {
					return this.songRepository.findBySongNameAndArtistNameAndDurationAndViewsAndLiked(songName, artistName, duration, views, liked);
				}
				else if(duration != null && views != null) {
					return this.songRepository.findBySongNameAndArtistNameAndDurationAndViews(songName, artistName, duration, views);
				}
				else if(duration != null && liked != null) {
					return this.songRepository.findBySongNameAndArtistNameAndDurationAndLiked(songName, artistName, duration, liked);
				}
				else if(views != null && liked != null) {
					return this.songRepository.findBySongNameAndArtistNameAndViewsAndLiked(songName, artistName, views, liked);
				}
				else if(duration != null) {
					return this.songRepository.findBySongNameAndArtistNameAndDuration(songName, artistName, duration);
				}
				else if(views != null) {
					return this.songRepository.findBySongNameAndArtistNameAndViews(songName, artistName, views);
				}
				else if(liked != null) {
					return this.songRepository.findBySongNameAndArtistNameAndLiked(songName, artistName, liked);
				}
				else {
					return this.songRepository.findBySongNameAndArtistName(songName, artistName);
				}
			}
			else if(duration != null) {
				if(views != null && liked != null) {
					return this.songRepository.findBySongNameAndDurationAndViewsAndLiked(songName, duration, views, liked);
				}
				else if(liked != null) {
					return this.songRepository.findBySongNameAndDurationAndLiked(songName, duration, liked);
				}
				else if(views != null) {
					return this.songRepository.findBySongNameAndDurationAndViews(songName, duration, views);
				}
				else {
					return this.songRepository.findBySongNameAndDuration(songName, duration);
				}
			}
			else if(views != null && liked != null) {
				return this.songRepository.findBySongNameAndViewsAndLiked(songName, views, liked);
			}
			else if(views != null) {
				return this.songRepository.findBySongNameAndViews(songName, views);
			}
			else if(liked != null) {
				return this.songRepository.findBySongNameAndLiked(songName, liked);
			}
			else {
				return this.songRepository.findBySongName(songName);
			}
		}
		else if(artistName != null) {
			if(duration != null) {
				if(liked != null && views != null) {
					return this.songRepository.findByArtistNameAndDurationAndViewsAndLiked(artistName, duration, views, liked);
				}
				else if(liked != null) {
					return this.songRepository.findByArtistNameAndDurationAndLiked(artistName, duration, liked);
				}
				else if(views != null) {
					return this.songRepository.findByArtistNameAndDurationAndViews(artistName, duration, views);
				}
				else {
					return this.songRepository.findByArtistNameAndDuration(artistName, duration);
				}
			}
			else if(liked != null) {
				if(views != null) {
					return this.songRepository.findByArtistNameAndViewsAndLiked(artistName, views, liked);
				}
				else return this.songRepository.findByArtistNameAndLiked(artistName, liked);
			}
			else if(views != null) {
				return this.songRepository.findByArtistNameAndViews(artistName, views);
			}
			else return this.songRepository.findByArtistName(artistName);
		}
		else if(duration != null) {
			if(liked != null) {
				if(views != null) {
					return this.songRepository.findByDurationAndViewsAndLiked(duration, views, liked);
				}
				else {
					return this.songRepository.findByDurationAndLiked(duration, liked);
				}
			}
			else if(views != null){
				return this.songRepository.findByDurationAndViews(duration, views);
			}
		}
		else if(views != null && liked != null) {
			return this.songRepository.findByViewsAndLiked(views, liked);
		}
		else if(views != null) {
			return this.songRepository.findByViews(views);
		}
		else if(liked != null) {
			return this.songRepository.findByLiked(liked);
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"need to enter something to search");
		
	}
	
	
	
	
	
	
	
}
