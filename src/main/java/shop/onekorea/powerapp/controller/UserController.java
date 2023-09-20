package shop.onekorea.powerapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import shop.onekorea.powerapp.dto.PatchUserRequestDto;
import shop.onekorea.powerapp.dto.PatchUserResponseDto;
import shop.onekorea.powerapp.dto.ResponseDto;
import shop.onekorea.powerapp.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    /// 2.  생성자 주입 방식으로 처리
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/rwkang")
    public String getUserid() {
        System.out.println("=====> UserController.java.getUserid()");
        String userid = userService.getUserid();
        System.out.println("=====> UserController.java.getUserid(): " + userid);

        return "<h1>" + userid + "</h1>";
    }

    @PatchMapping("/")
    public ResponseDto<PatchUserResponseDto> patchUser(@RequestBody PatchUserRequestDto requestBody, @AuthenticationPrincipal String email) {
        return null;
    }

}


/*
package shop.onekorea.powerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import shop.onekorea.powerapp.config.auth.LoginUser;
import shop.onekorea.powerapp.dto.PatchUserRequestDto;
import shop.onekorea.powerapp.dto.PatchUserResponseDto;
import shop.onekorea.powerapp.dto.ResponseDto;
import shop.onekorea.powerapp.dto.UserResponseDto;
import shop.onekorea.powerapp.entity.UserEntity;
import shop.onekorea.powerapp.filter.jwt.JwtProcess;
import shop.onekorea.powerapp.filter.jwt.JwtProps;
import shop.onekorea.powerapp.handler.ex.CustomApiException;
import shop.onekorea.powerapp.repository.UserRepository;
import shop.onekorea.powerapp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

//@RequestMapping("/api")
@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired private UserRepository userRepository;

    /// 2.  생성자 주입 방식으로 처리
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/rwkang")
    public String getUserid() {
        System.out.println("=====> UserController.java.getUserid()");
        String userid = userService.getUserid();
        System.out.println("=====> UserController.java.getUserid(): " + userid);

        return "<h1>" + userid + "</h1>";
    }

    @GetMapping("/users")
    public ResponseDto<ResponseDto> initUser() {
        System.out.println("=====> initUser: ");

        ResponseDto responseDto = userService.initUser();

        return responseDto.setSuccess("회원 목록", responseDto);

    }

    // 2023.08.22 Conclusion. 일단 전체 데이터를 뿌리고, 나중에는,
    // 1. 최근 기준 top 10명만 뿌리게 하거나,
    // 2. 올해(이번달 또는 지정일자 이후) 가입한 user 데이터만 뿌리게 한다.
//    @GetMapping("/users")
//    public ResponseDto<ResponseDto> initUser() {
//        System.out.println("=====> initUser: ");
//
//        ResponseDto responseDto = userService.initUser();
////        List<UserEntity> usersList = userService.initUser();
////        List<UserResponseDto> userResponseDtoList = usersList.stream().map(UserResponseDto::new).collect(Collectors.toList());
//
////        UserEntity userEntity = responseDto;
////        userEntity.setPassword(null); // password는 돌려주지 않는다. (컨트롤러에서 변경했기 때문에 dirty checking 안됨)
//
//        System.out.println("UserController.java.41.responseDto.toString(): ${responseDto.toString()}");
//        return responseDto.setSuccessNoToken("회원 목록 리스트", responseDto);
////        return ResponseDto.setSuccess(1,"사원목록보기", responseDto);
//
////        List<UserEntity> users = userService.serviceGetUserList();
////        List<UserResponseDto> listUserResponseDto = listUserEntity.stream().map(UserResponseDto::new).collect(Collectors.toList());
////        List<UserResponseDto> listUserResponseDto = users.stream().map(UserResponseDto::new).collect(Collectors.toList());
////        return new ResponseDto<>(true, "목록보기완료", listUserResponseDto);
////        return new ResponseDto<>(1, "목록보기완료", listUserResponseDto);
//    }

    @PatchMapping("/userid")
//    public ResponseDto<PatchUserResponseDto> patchUser(@RequestBody PatchUserRequestDto requestBody, @AuthenticationPrincipal String email) {
    public ResponseDto<PatchUserResponseDto> patchUser(@RequestBody PatchUserRequestDto requestBody, @AuthenticationPrincipal String userid) {
        System.out.println("=====> patchUser: ${userid}.toString()");
        System.out.println("=====> patchUser: ${requestBody}.toString()");

        ResponseDto responseDto = userService.patchUser(requestBody, userid);
        return null;
    }

    // @LoginUse로 세션 정보 접근 가능!!
//    @GetMapping("user/{userid}")
//    public ResponseDto getUser(String userid) {
//        return null;
//    }

    @GetMapping("/user/{userid}")
    public ResponseDto<?> updateUser(@PathVariable String userid, @RequestBody UserEntity userEntityRequest, @LoginUser UserEntity principal) {
        if (principal.getUserid() == userid) {
            UserEntity userEntity = userService.updateUser(userid, userEntityRequest);
            return ResponseDto.setSuccess("회원 정보 수정", new UserResponseDto(userEntity));

        } else {
            return ResponseDto.setFailed(null);
        }
    }


    @GetMapping("/jwtToken")
    public ResponseDto<?> jwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("authorization");
        if (jwtToken == null) {
            throw new CustomApiException("토큰이 헤더에 없습니다.");
        }
        System.out.println("토큰이 헤더 있습니다.");
        jwtToken = jwtToken.replace(JwtProps.AUTH, "");
        String userid = JwtProcess.verify(jwtToken);
//        int userId = JwtProcess.verify(jwtToken);
        UserEntity userEntity = userRepository.findById(userid).orElseThrow(() -> new CustomApiException("토큰 검증 실패"));
        return ResponseDto.setSuccess("회원정보전달완료", new UserResponseDto(userEntity));
//        return new CMRespDto<>(1, "회원정보전달완료", new UserRespDto(userEntity));
    }

//    @GetMapping("/init/user")
//    public ResponseDto<?> initUser() {
//        List<UserEntity> usersList = userService.initUser();
//        List<UserResponseDto> userResponseDtoList = usersList.stream().map(UserResponseDto::new).collect(Collectors.toList());
//
//        return ResponseDto.setSuccessNoToken("회원 목록 리스트", userResponseDtoList);
//    }


}


 */