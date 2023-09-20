package shop.onekorea.powerapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 회원 가입
// ResponseDto<D> => result, message, data { token, exprTime(토큰 만료 시간) }

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto { // 회원 가입

//    private String email;
    private String userid;
    private String password;
    private String passwordConfirmation;
    private String nickname;
    private String phoneNo;
    private String address;
    private String addressDetail;

    /// 2023.08.28 Conclusion. "SignUp.회원 가입"에서는 아래 2개 컬럼(upodated, created)은 값이 들어 오지 않기 때문에, 여기에 정리할 필요 조차 없다.
//    private LocalDateTime updated;
//    private String updated;
//    private LocalDateTime created;
//    private String created;

}



/*
package shop.onekorea.powerapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.onekorea.powerapp.entity.UserEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// 회원 가입
// ResponseDto<D> => result, message, data { token, exprTime(토큰 만료 시간) }

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto { // 회원 가입

//    private String email;
    private String userid;
    private String password;
    private String passwordConfirmation;
    private String nickname;
    private String phoneNo;
    private String address;
//    private String addressDetail;
//    private String updated;
//    private String created;
    private LocalDateTime updated;
    private LocalDateTime created;

//    public SignUpRequestDto(SignUpRequestDto signUpRequestDto) {
    public SignUpRequestDto(UserEntity userEntity) {
//        this.email = userEntity.getEmail();
        this.userid = userEntity.getUserid();
        this.password = userEntity.getPassword();
        this.nickname = userEntity.getNickname();
        this.phoneNo = userEntity.getPhoneNo();
        this.address = userEntity.getAddress(); // + " " + userEntity.getAddressDetail();
        this.updated = userEntity.getUpdated();
        this.created = userEntity.getCreated();
//        this.updated = userEntity.getUpdated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        this.created = userEntity.getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        this.updated = new Date();
    }

}


 */