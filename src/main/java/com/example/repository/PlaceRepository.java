package com.example.repository;
import com.example.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByName(String name);
}