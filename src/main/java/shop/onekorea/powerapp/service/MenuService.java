package shop.onekorea.powerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.onekorea.powerapp.dto.ResponseDto;
import shop.onekorea.powerapp.entity.MenuEntity;
import shop.onekorea.powerapp.entity.PopularSearchEntity;
import shop.onekorea.powerapp.repository.MenuRepository;
import shop.onekorea.powerapp.repository.PopularSearchRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;
//    @Autowired PopularSearchRepository popularSearchRepository;

    // 2023.08.04 Added. 내 user_id 메뉴만 불러온다.
//    public ResponseDto<List<MenuEntity>> serviceGetSearchList(String email) {
    public ResponseDto<List<MenuEntity>> serviceGetSearchList(String userid) {

        List<MenuEntity> menuList = new ArrayList<MenuEntity>();
        try {
            //menuList = menuRepository.existsByUserIdOrderByUserIdAndCode(email);
//            menuList = menuRepository.findByUserIdContainsOrderByUserIdAndCode(email);
//            menuList = menuRepository.findByUserIdContainsOrderByUserId(email);
            menuList = menuRepository.findByUseridContainsOrderByUserid(userid);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }

        System.out.println("MenuController.java.menuList: ${menuList}");
        return ResponseDto.setSuccess("성공!!!", menuList);

    }







//    public ResponseDto<List<MenuEntity>> serviceGetTop3() {
//
//        // 2023.08.02 Conclusion. *** "List"는 Interface이기 때문에, "List()" 자체로 new 할 수 없고,
//        // 반드시 "List"를 상속 받아서 "Implements.구현"한 "ArryList()"로 생성해야 한다.
//        //      public interface List<E> extends Collection<E>
//        //      public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable
//        // =================================================================================================>
//        List<MenuEntity> menuList = new ArrayList<MenuEntity>();
//
//        // Date date = new Date();
//        // date.setDate(date.getDate() -7); // 이것은 단종될 것이라고 하니, 아래거로 사용한다.
//        Date date = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
//
//        List<MenuEntity> menuList = null;
//        try {
//            menuList = menuRepository.findTop3ByCreatedAfterOrderByThumbsUpCountDesc(date); // DESC: 역순으로 나오게.
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed("Database Error");
//        }
//        return ResponseDto.setSuccess("성공!!!", boardLsit);
//
//    }

//    public ResponseDto<List<MenuEntity>> serviceGetList() {
//
//        List<MenuEntity> menuList = new ArrayList<MenuEntity>();
//        try {
//            menuList = menuRepository.findByOrderByCreatedDesc(); // DESC: 역순으로 나오게.
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed("Database Error");
//        }
//
//        return ResponseDto.setSuccess("성공!!!", boardList);
//    }

//    public ResponseDto<List<PopularSearchEntity>> serviceGetPopularSearchList() {
//
//        List<PopularSearchEntity> popularSearchList = new ArrayList<PopularSearchEntity>();
//
//        try {
//            popularSearchList = popularSearchRepository.findTop10ByOrderBySearchCountDesc(); // DESC: 역순으로 나오게.
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed("Database Error");
//        }
//
//        return ResponseDto.setSuccess("성공!!!", popularSearchList);
//    }

//    public ResponseDto<List<MenuEntity>> serviceGetSearchList(String title) {
//
//        List<MenuEntity> menuList = new ArrayList<MenuEntity>();
//        try {
//            menuList = menuRepository.findByTitleContains(title);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed("Database Error");
//        }
//
//        return ResponseDto.setSuccess("성공!!!", menuList);
//
//    }

}
