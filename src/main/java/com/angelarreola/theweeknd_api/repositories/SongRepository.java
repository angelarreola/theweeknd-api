package com.angelarreola.theweeknd_api.repositories;

import com.angelarreola.theweeknd_api.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SongRepository  extends JpaRepository<Song, UUID> {
    Optional<Song> findByTitle (String songTitle);
}
