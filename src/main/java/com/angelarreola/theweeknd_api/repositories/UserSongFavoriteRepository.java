package com.angelarreola.theweeknd_api.repositories;

import com.angelarreola.theweeknd_api.entities.UserSongFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserSongFavoriteRepository extends JpaRepository<UserSongFavorite, UUID> {}
