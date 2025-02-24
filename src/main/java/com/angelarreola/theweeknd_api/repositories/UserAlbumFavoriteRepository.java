package com.angelarreola.theweeknd_api.repositories;

import com.angelarreola.theweeknd_api.entities.UserAlbumFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAlbumFavoriteRepository extends JpaRepository<UserAlbumFavorite, UUID> {}
