package shop.onekorea.powerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.onekorea.powerapp.entity.ThumbsUpEntity;

@Repository
public interface ThumbsUpRepository extends JpaRepository<ThumbsUpEntity, Integer> {
}
