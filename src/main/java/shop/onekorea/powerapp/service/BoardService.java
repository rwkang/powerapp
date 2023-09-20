package shop.onekorea.powerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.onekorea.powerapp.dto.ResponseDto;
import shop.onekorea.powerapp.entity.BoardEntity;
import shop.onekorea.powerapp.entity.PopularSearchEntity;
import shop.onekorea.powerapp.repository.BoardRepository;
import shop.onekorea.powerapp.repository.PopularSearchRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class BoardService {

    @Autowired BoardRepository boardRepository;
    @Autowired PopularSearchRepository popularSearchRepository;

    public ResponseDto<List<BoardEntity>> serviceGetTop3() {
        // public ResponseDto<List<BoardEntity>> getTop3() { // 14강

        // 2023.08.02 Conclusion. *** "List"는 Interface이기 때문에, "List()" 자체로 new 할 수 없고,
        // 반드시 "List"를 상속 받아서 "Implements.구현"한 "ArryList()"로 생성해야 한다.
        //      public interface List<E> extends Collection<E>
        //      public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable
        // =================================================================================================>
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();

        // Date date = new Date();
        // date.setDate(date.getDate() -7); // 이것은 단종될 것이라고 하니, 아래거로 사용한다.
        Date date = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));

        List<BoardEntity> boardLsit = null;
        try {
            boardList = boardRepository.findTop3ByCreatedAfterOrderByThumbsUpCountDesc(date); // DESC: 역순으로 나오게.
            //boardList = boardRepository.findTop3ByBoardWriteDateAfterOrderByLikesCntDesc(null); // 14강
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }
        return ResponseDto.setSuccess("성공!!!", boardLsit);

    }

    public ResponseDto<List<BoardEntity>> serviceGetList() {
        // public ResponseDto<List<BoardEntity>> getList() { // 14강

        List<BoardEntity> boardList = new ArrayList<BoardEntity>();
        try {
            boardList = boardRepository.findByOrderByCreatedDesc(); // DESC: 역순으로 나오게.
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }

        return ResponseDto.setSuccess("성공!!!", boardList);
    }

    public ResponseDto<List<PopularSearchEntity>> serviceGetPopularSearchList() {
        // public ResponseDto<List<PopularSearchRepository>> getPopularSearchList() { // 14강

        List<PopularSearchEntity> popularSearchList = new ArrayList<PopularSearchEntity>();

        try {
            popularSearchList = popularSearchRepository.findTop10ByOrderBySearchCountDesc(); // DESC: 역순으로 나오게.
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }

        return ResponseDto.setSuccess("성공!!!", popularSearchList);
    }

    public ResponseDto<List<BoardEntity>> serviceGetSearchList(String title) {

        List<BoardEntity> boardList = new ArrayList<BoardEntity>();
        try {
            boardList = boardRepository.findByTitleContains(title);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }

        return ResponseDto.setSuccess("성공!!!", boardList);

    }

}
