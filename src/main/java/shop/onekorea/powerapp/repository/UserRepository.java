package shop.onekorea.powerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.onekorea.powerapp.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> { // 여기서 "UserEntity"는 엔터티명.

    // 2023.07.28 Conclusion. 여기서 특히 주의할 사항은, "메소드 명"을 아무렇게나 사용해서는 안 된다는 것이다. 에러 발생 함
    // public boolean isExistedByEmailAndPassword(String email, String password); // 이런 "메소드 명"은 에러 나네

    // 2023.07.28 Conclusion. getBy~, findBy~, existsBy~ 속도는 어는 것이 빠를까?
    // https://velog.io/@_koiil/JPA-existById-vs.-getById-vs.-findById
    // 1. 존재 여부를 확인할 때는 existBy
    // 2. Id 값만 사용할 엔티티가 필요한 경우에는 getBy => 그런데, 아래처럼 [password]로 getByPassword를 사용하면, 받아오질 못하네.
    // 3. Id 이외의 데이터도 사용하는 엔티티의 경우에는 findBy를 사용하면 되겠습니다! => 그런데, 아래처럼 [password]로 findByPassword를 사용하면, 받아오질 못하네.

//    public boolean existsByEmailAndPassword(String email, String password); // OK
    // => SELECT * FROM user WHERE email = :email AND password = :password;

    public boolean existsByUseridAndPassword(String userid, String password); // OK
    // => SELECT * FROM user WHERE userid = :userid AND password = :password;

    //    public boolean findByEmailAndPassword(String email, String password); // NG
//    public boolean getByPassword(String password); // NG
    public boolean existsByPassword(String password); // OK
    // => SELECT * FROM user WHERE password = :password;
//    public boolean findByPassword(String password); // NG


    // 2023.08.01 Conclusion. SignUp.회원 가입 Request 정보를 받아서, password.비밀 번호를 암호화한 후에는,
    // 위의 email+password 조합을 쓸 수 없고, 'email' 1개로 검색하여, 해당 하는 정보를 가져온다.
//    public UserEntity findByEmail(String email);
    // => SELECT * FROM user WHERE email = :email;

    /// 2023.08.28 Added.
    public UserEntity findByUserid(String userid);
    // => SELECT * FROM user WHERE userid = :userid

}



/*
package shop.onekorea.powerapp.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.onekorea.powerapp.entity.UserEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> { // 여기서 "UserEntity"는 엔터티명.

    // 2023.07.28 Conclusion. 여기서 특히 주의할 사항은, "메소드 명"을 아무렇게나 사용해서는 안 된다는 것이다. 에러 발생 함
    // public boolean isExistedByEmailAndPassword(String email, String password); // 이런 "메소드 명"은 에러 나네

    // 2023.07.28 Conclusion. getBy~, findBy~, existsBy~ 속도는 어는 것이 빠를까?
    // https://velog.io/@_koiil/JPA-existById-vs.-getById-vs.-findById
    // 1. 존재 여부를 확인할 때는 existBy
    // 2. Id 값만 사용할 엔티티가 필요한 경우에는 getBy => 그런데, 아래처럼 [password]로 getByPassword를 사용하면, 받아오질 못하네.
    // 3. Id 이외의 데이터도 사용하는 엔티티의 경우에는 findBy를 사용하면 되겠습니다! => 그런데, 아래처럼 [password]로 findByPassword를 사용하면, 받아오질 못하네.

//    public boolean existsByEmailAndPassword(String email, String password); // OK
//    public boolean existsByEmailAndPassword(String userid, String password); // OK
    public boolean existsByUseridAndPassword(String userid, String password); // OK
    // => SELECT * FROM user WHERE email = :email AND password = :password;

    //    public boolean findByEmailAndPassword(String email, String password); // NG
//    public boolean getByPassword(String password); // NG
    public boolean existsByPassword(String password); // OK
    // => SELECT * FROM user WHERE password = :password;
//    public boolean findByPassword(String password); // NG


    // 2023.08.01 Conclusion. SignUp.회원 가입 Request 정보를 받아서, password.비밀 번호를 암호화한 후에는,
    // 위의 email+password 조합을 쓸 수 없고, 'email' 1개로 검색하여, 해당 하는 정보를 가져온다.
    // public UserEntity findByEmail(String email);
    // => SELECT * FROM user WHERE email = :email;
    public UserEntity findByUserid(String userid);
    // => SELECT * FROM user WHERE userid = :userid;

    // 2023.08.22 Conclusion. 일단 전체 데이터를 뿌리고, 나중에는,
    // 1. 최근 기준 top 10명만 뿌리게 하거나,
    // 2. 올해(이번달 또는 지정일자 이후) 가입한 user 데이터만 뿌리게 한다.

     public List<UserEntity> findByOrderByCreatedDesc();
    // => SELECT * FROM user;

    public List<UserEntity> findTop10ByOrderByCreatedDesc();
    // => SELECT * FROM user ORDER BY created DESC LIMIT 10;

    public List<UserEntity> findTop10ByCreatedAfterOrderByCreatedDesc(Date date);
    // => SELECT * FROM user ORDER BY created DESC LIMIT 10;

}

 */
