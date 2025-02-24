package com.angelarreola.theweeknd_api.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_album")
public class UserAlbumFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;

    public UserAlbumFavorite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserAlbumFavorite that = (UserAlbumFavorite) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(album, that.album) && Objects.equals(addedAt, that.addedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, album, addedAt);
    }

    @Override
    public String toString() {
        return "UserAlbumFavorite{" +
                "id=" + id +
                ", user=" + user +
                ", album=" + album +
                ", addedAt=" + addedAt +
                '}';
    }
}
