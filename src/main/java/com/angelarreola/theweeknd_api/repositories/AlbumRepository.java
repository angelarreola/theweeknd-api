package com.angelarreola.theweeknd_api.repositories;

import com.angelarreola.theweeknd_api.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
    Optional<Album> findByName(String albumName);
}
