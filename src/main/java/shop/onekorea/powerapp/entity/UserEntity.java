package shop.onekorea.powerapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import shop.onekorea.powerapp.dto.SignUpRequestDto;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User") // 꼭 import javax.persistence.Entity; 이것으로 import.
@Table(name = "user") // 실제 테이블 이름.
public class UserEntity {
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 옵션
    // private String email;
    @Id // 바로 아래 컬럼을 "PRIMARY KEY"로 설정.
    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String password;

    private String nickname;
    private String phoneNo;
    private String address;
    private String profile;

    private Date updated;
    // Date date = new Date();


    /// 2023.08.28 Conclusion. 결론적으로, "LocalDateTime" 타입은 사용할 수가 없네요.

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-ddTHH:mm:ss", timezone = "Asia/Seoul")
//    @CreationTimestamp
//    private LocalDateTime updated;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-ddTHH:mm:ss", timezone = "Asia/Seoul")
//    @CreationTimestamp
//    private LocalDateTime created;

//    LocalDateTime localDateTime = LocalDateTime.now(Clock.systemDefaultZone());
//    LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));


    public UserEntity(SignUpRequestDto signUpRequestDto) {
//        this.email = signUpRequestDto.getEmail();
        this.userid = signUpRequestDto.getUserid();
        this.password = signUpRequestDto.getPassword();
        this.nickname = signUpRequestDto.getNickname();
        this.phoneNo = signUpRequestDto.getPhoneNo();
        this.address = signUpRequestDto.getAddress(); // + " " + signUpRequestDto.getAddressDetail();

        this.updated = new Date();

        /// 2023.08.28 Conclusion. 결론적으로, "LocalDateTime" 타입은 사용할 수가 없네요.

        /// 2023.08.28 Conclusion. "SignUp.회원 가입"에서는 아래 2개 컬럼(upodated, created)은 "signUpRequestDto"에서, 절대 받아 올 수가 없다.
//        this.updated = LocalDateTime.parse(String.format(localDateTime.toString(), DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss")));
//        this.updated = localDateTime;
        /// this.created = date;  /// 여기 "created" 컬럼은, "DB"에서 직접 핸들링 하므로, 여기서 굳이 처리할 필요가 없다.


//        this.updated = LocalDateTime.parse(String.format(signUpRequestDto.getUpdated(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        this.created = LocalDateTime.parse(String.format(signUpRequestDto.getCreated(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        this.updated = LocalDateTime.parse(signUpRequestDto.getUpdated().format(String.valueOf(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        this.created = LocalDateTime.parse(signUpRequestDto.getCreated().format(String.valueOf(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

    }

}
