
/*
package shop.onekorea.powerapp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import shop.onekorea.powerapp.entity.UserEntity;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RequiredArgsConstructor
@Data
public class UserResponseDto<T> {
    //    private String email;
    private String userid;
    private String password;
    private String passwordConfirmation;
    private String nickname;
    private String phoneNo;
    private String address;
//    private String addressDetail;
    private LocalDateTime updated;
    private LocalDateTime created;

    //    public SignUpRequestDto(SignUpRequestDto signUpRequestDto) {
    public UserResponseDto(UserEntity userEntity) {
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
