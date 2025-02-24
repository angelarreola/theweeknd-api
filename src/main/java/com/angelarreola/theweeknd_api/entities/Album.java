package com.angelarreola.theweeknd_api.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private String genre;

    private String coverImage;

    private final String artist = "The Weeknd"; // This may be variable if we want to add more artists

    @Column(nullable = false)
    private int nTracks;

    @OneToMany(mappedBy = "album")
    private Set<Song> songs;


    public Album() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getArtist() {
        return artist;
    }

    public int getnTracks() {
        return nTracks;
    }

    public void setnTracks(int nTracks) {
        this.nTracks = nTracks;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return nTracks == album.nTracks && Objects.equals(id, album.id) && Objects.equals(name, album.name) && Objects.equals(releaseDate, album.releaseDate) && Objects.equals(genre, album.genre) && Objects.equals(coverImage, album.coverImage) && Objects.equals(artist, album.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, genre, coverImage, artist, nTracks);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", genre='" + genre + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", artist='" + artist + '\'' +
                ", nTracks=" + nTracks +
                '}';
    }
}
