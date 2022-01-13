package repository;
import org.springframework.data.repository.CrudRepository;
import entities.Song;
import java.util.List;


public interface SongRepository extends CrudRepository<Song,Integer> {

	List<Song> findBySongName(String songName);
	List<Song> findByArtistName(String artistName);
	List<Song> findByDuration(Integer duration);
	List<Song> findByViews(Integer views);
	List<Song> findByLiked(Boolean liked);
	List<Song> findByAlive(Boolean alive);
	List<Song> findByReleaseYear(Integer releaseYear);
	List<Song> findByDurationGreaterThan(Integer minDuration);
	List<Song> findByViewsGreaterThan(Integer minViews);
	List<Song> findByReleaseYearGreaterThan(Integer minYear);
	List<Song> findBySongNameAndArtistNameAndDurationAndViewsAndLiked(String songName,String artistName,Integer duration,Integer views,Boolean liked);
	List<Song> findBySongNameAndDurationAndViewsAndLiked(String songName,Integer duration,Integer views,Boolean liked);
	List<Song> findBySongNameAndViewsAndLiked(String songName,Integer views, Boolean liked);
	List<Song> findBySongNameAndLiked(String songName,Boolean liked);
	List<Song> findBySongNameAndArtistNameAndViewsAndLiked(String songName,String artistName,Integer views,Boolean liked);
	List<Song> findBySongNameAndArtistNameAndLiked(String songName,String artistName,Boolean liked);
	List<Song> findBySongNameAndArtistName(String songName,String artistName);
	List<Song> findBySongNameAndArtistNameAndDuration(String songName,String artistName, Integer duration);
	List<Song> findBySongNameAndDurationAndLiked(String songName,Integer duration,Boolean liked);
	List<Song> findBySongNameAndDurationAndViews(String songName,Integer duration,Integer views);
	List<Song> findBySongNameAndDuration(String songName,Integer duration);
	List<Song> findBySongNameAndArtistNameAndDurationAndViews(String songName,String artistName,Integer duration,Integer views);
	List<Song> findBySongNameAndArtistNameAndViews(String songName,String artistName,Integer views);
	List<Song> findBySongNameAndViews(String songName,Integer views);
	List<Song> findBySongNameAndArtistNameAndDurationAndLiked(String songName,String artistName,Integer duration,Boolean liked);
	List<Song> findByArtistNameAndDurationAndViewsAndLiked(String artistName,Integer duration,Integer views,Boolean liked);
	List<Song> findByArtistNameAndDurationAndViews(String artistName,Integer duration,Integer views);
	List<Song> findByArtistNameAndDuration(String artistName,Integer duration);
	List<Song> findByArtistNameAndDurationAndLiked(String artistName,Integer duration,Boolean liked);
	List<Song> findByArtistNameAndLiked(String artistName,Boolean liked);
	List<Song> findByArtistNameAndViewsAndLiked(String artistName,Integer views,Boolean liked);
	List<Song> findByArtistNameAndViews(String artistName,Integer views);
	List<Song> findByDurationAndViewsAndLiked(Integer duration,Integer views,Boolean liked);
	List<Song> findByDurationAndViews(Integer duration,Integer views);
	List<Song> findByDurationAndLiked(Integer duration,Boolean liked);
	List<Song> findByViewsAndLiked(Integer views,Boolean liked);
	List<Song> findByReleaseYearBetween(Integer minYear, Integer maxYear);
	
}
