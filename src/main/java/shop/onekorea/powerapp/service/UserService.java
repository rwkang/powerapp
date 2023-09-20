package shop.onekorea.powerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.onekorea.powerapp.dto.PatchUserRequestDto;
import shop.onekorea.powerapp.dto.PatchUserResponseDto;
import shop.onekorea.powerapp.dto.ResponseDto;
import shop.onekorea.powerapp.entity.UserEntity;
import shop.onekorea.powerapp.repository.UserRepository;

@Service
public class UserService {

    @Autowired UserRepository userRepository;

    public String getUserid() {

        return "rwkang@naver.com";
    }

//    public ResponseDto<PatchUserResponseDto> patchUser(PatchUserRequestDto requestDto, String email) {
    public ResponseDto<PatchUserResponseDto> patchUser(PatchUserRequestDto requestDto, String userid) {

        UserEntity userEntity = null;
        String nickname = requestDto.getNickname();
        String profile = requestDto.getProfile();

        try {
//            userEntity = userRepository.findByEmail(email);
            userEntity = userRepository.findByUserid(userid);
            if (userEntity == null) {
                return ResponseDto.setFailed("User not found");
            }

            userEntity.setNickname(nickname);
            userEntity.setProfile(profile);

            userRepository.save(userEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error: " + e.getMessage());
        }

        userEntity.setPassword("");

        PatchUserResponseDto patchUserResponseDto = new PatchUserResponseDto(userEntity);

        return ResponseDto.setSuccess("성공!!!", patchUserResponseDto);

    }

}



/*
package shop.onekorea.powerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.onekorea.powerapp.dto.PatchUserRequestDto;
import shop.onekorea.powerapp.dto.PatchUserResponseDto;
import shop.onekorea.powerapp.dto.ResponseDto;
import shop.onekorea.powerapp.dto.UserResponseDto;
import shop.onekorea.powerapp.entity.UserEntity;
import shop.onekorea.powerapp.handler.ex.CustomApiException;
import shop.onekorea.powerapp.repository.UserRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public String getUserid() {

        return "rwkang@naver.com";
    }

    @Transactional(readOnly = true)
    public ResponseDto<List<UserEntity>> initUser() {

        List<UserEntity> userList = new ArrayList<>();

        // Date date = new Date();
        // date.setDate(date.getDate() -7); // 이것은 단종될 것이라고 하니, 아래거로 사용한다.
        Date date = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));

        try {
//            userList = userRepository.findByOrderByCreatedDesc(); // 전체 데이터
            userList = userRepository.findTop10ByCreatedAfterOrderByCreatedDesc(date);
//            userList = userRepository.findTop10ByOrderByCreatedDesc();

            System.out.println("UserService.java.41.userList.toString(): ${userList.toString()}");
            System.out.println("UserService.java.41.data: ${data}");

            return ResponseDto.setSuccess("사용자 목록", userList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }

//        return userRepository.findTop10ByOrderByCreatedDesc();
//        return userRepository.findTop10ByOrderByCreatedDesc(Sort.by(Sort.Direction.DESC, "created"));
    }

//    public ResponseDto<PatchUserResponseDto> patchUser(PatchUserRequestDto requestDto, String email) {
    public ResponseDto<PatchUserResponseDto> patchUser(PatchUserRequestDto requestDto, String useridRequest) {

        UserEntity userEntity = null;
//        String nickname = requestDto.getNickname();
        String userid = requestDto.getUserid();
        String profile = requestDto.getProfile();

        try {
//            userEntity = userRepository.findByEmail(email);
            userEntity = userRepository.findByUserid(userid);
            if (userEntity == null) {
                return ResponseDto.setFailed("User not found");
            }

            userEntity.setUserid(userid);
            userEntity.setProfile(profile);

            userRepository.save(userEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error: " + e.getMessage());
        }

        userEntity.setPassword("");

        PatchUserResponseDto patchUserResponseDto = new PatchUserResponseDto(userEntity);

        String token = "token";

        return ResponseDto.setSuccess("성공!!!", patchUserResponseDto);

    }

    public UserEntity updateUser(String userid, UserEntity userEntityRequest) {

        try {
            UserEntity userEntity = userRepository.findByUserid(userid);
            return userEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public UserEntity initUser() {
//        UserEntity userEntity = userRepository.
//    }

}

 */
