package shop.onekorea.powerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.onekorea.powerapp.entity.MenuEntity;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, String> {

    // SELECT * FROM menu WHERE user_id =? ORDER BY user_id ASC;
    // public List<MenuEntity> existsByUserIdOrderByUserIdAndCode(String email);
//    public List<MenuEntity> findByUserIdContainsOrderByUserIdAndCode(String email);
//    public List<MenuEntity> findByUserIdContainsOrderByUserId(String email);
    public List<MenuEntity> findByUseridContainsOrderByUserid(String userid);

}
