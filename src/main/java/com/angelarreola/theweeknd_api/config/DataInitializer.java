package com.angelarreola.theweeknd_api.config;

import com.angelarreola.theweeknd_api.entities.*;
import com.angelarreola.theweeknd_api.repositories.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Component
public class DataInitializer {
//    private final RoleRepository roleRepository;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.roleRepository = roleRepository;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private UserSongFavoriteRepository userSongFavoriteRepository;
    @Autowired
    private UserAlbumFavoriteRepository userAlbumFavoriteRepository;

    @PostConstruct
    public void initData() {

        Album album1 = albumRepository.findByName("Dawn FM").orElseGet(() -> {
            Album album = new Album();
            album.setName("Dawn FM");
            album.setReleaseDate(LocalDate.now());
            album.setGenre("RB&B");
            album.setnTracks(16);
            return albumRepository.save(album);
        });

        Song song1 = songRepository.findByTitle("Take My Breath").orElseGet(() -> {
            Song song = new Song();
            song.setTitle("Take My Breath");
            song.setAlbum(album1);
            song.setGenre("RB&B");
            song.setDuration(210);
            song.setReleaseDate(LocalDate.now());
            song.setTrackNumber(3);
            return songRepository.save(song);
        });

        Song song2 = songRepository.findByTitle("Out of Time").orElseGet(() -> {
            Song song = new Song();
            song.setTitle("Out of Time");
            song.setAlbum(album1);
            song.setGenre("RB&B");
            song.setDuration(186);
            song.setReleaseDate(LocalDate.now());
            song.setTrackNumber(9);
            return songRepository.save(song);
        });

        /* Create roles if they don't exist */
        Role adminRole = roleRepository.findByName("ADMIN").orElseGet(() -> {
            Role role = new Role();
            role.setName("ADMIN");
            role.setDescription("Administrator role");
            return roleRepository.save(role);
        });

        Role userRole = roleRepository.findByName("USER").orElseGet(() -> {
            Role role = new Role();
            role.setName("USER");
            role.setDescription("Regular user role");
            return roleRepository.save(role);
        });

        Role assistantRole = roleRepository.findByName("ASSISTANT").orElseGet(() -> {
           Role role = new Role();
           role.setName("ASSISTANT");
           role.setDescription("Assistant role");
           return roleRepository.save(role);
        });

        // Create users if there is no one.
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("user1").isEmpty()) {
            User user1 = new User();
            user1.setFirstName("User");
            user1.setLastName("One");
            user1.setUsername("user1");
            user1.setEmail("user1@example.com");
            user1.setPassword(passwordEncoder.encode("user123"));
            user1.setRoles(Set.of(userRole));
            userRepository.save(user1);
            addFavoriteSongsAndAlbums(user1, album1, song1, song2);
        }

        if (userRepository.findByUsername("user2").isEmpty()) {
            User user2 = new User();
            user2.setFirstName("User2");
            user2.setLastName("Two");
            user2.setUsername("user2");
            user2.setEmail("user2@example.com");
            user2.setPassword(passwordEncoder.encode("user123"));
            user2.setRoles(Set.of(userRole));
            userRepository.save(user2);
            addFavoriteSongsAndAlbums(user2, album1, song1, song2);
        }

        if (userRepository.findByUsername("assistant").isEmpty()) {
            User assistant = new User();
            assistant.setFirstName("Assistant");
            assistant.setLastName("Helper");
            assistant.setUsername("assistant");
            assistant.setEmail("assistant@example.com");
            assistant.setPassword(passwordEncoder.encode("assistant123"));
            assistant.setRoles(Set.of(assistantRole));
            userRepository.save(assistant);
            addFavoriteSongsAndAlbums(assistant, album1, song1, song2);
        }

        System.out.println("✅ Initial data loaded successfully");
    }

    private void addFavoriteSongsAndAlbums(User user, Album album, Song... songs) {
        UserAlbumFavorite albumFavorite = new UserAlbumFavorite();
        albumFavorite.setUser(user);
        albumFavorite.setAlbum(album);
        albumFavorite.setAddedAt(LocalDateTime.now());
        userAlbumFavoriteRepository.save(albumFavorite);

        for (Song song : songs) {
            UserSongFavorite songFavorite = new UserSongFavorite();
            songFavorite.setUser(user);
            songFavorite.setSong(song);
            songFavorite.setAddedAt(LocalDateTime.now());
            userSongFavoriteRepository.save(songFavorite);
        }
    }

}
