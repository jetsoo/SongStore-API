package entities;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SONG")
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="SONGNAME")
	private String songName;
	@Column(name="ARTISTNAME")
	private String artistName;
	@Column(name="DURATION")
	private Integer duration;
	@Column(name="LIKED")
	private Boolean liked;
	@Column(name="VIEWS")
	private Integer views;
	@Column(name="RELEASEYEAR")
	private Integer releaseYear;
	@Column(name="ARTISTALIVE")
	private Boolean alive = true;
	@Column(name="PRICE")
	private Float price;
	
	
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Boolean getAlive() {
		return alive;
	}
	public void setAlive(Boolean alive) {
		this.alive = alive;
	}
	public Integer getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	public Integer getId() {
		return this.id;
	}
	public String getSongName() {
		return this.songName;
	}
	public void setSongName(String songName) {
		this.songName=songName;
	}
	public String getArtistName() {
		return this.artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName=artistName;
	}
	public Integer getDuration() {
		return this.duration;
	}
	public void setDuration(Integer duration) {
		this.duration=duration;
	}
	public Boolean getLiked() {
		return this.liked;
	}
	public void setLiked(Boolean liked) {
		this.liked=liked;
	}
	public Integer getViews() {
		return this.views;
	}
	public void setViews(Integer views) {
		this.views=views;
	}
	@Override
	public String toString() {
		return "Song [id=" + id + ", songName=" + songName + ", artistName=" + artistName + ", duration=" + duration
				+ ", liked=" + liked + ", views=" + views + ", releaseYear=" + releaseYear + ", alive=" + alive
				+ ", price=" + price + "]";
	}
	
}
