package shop.onekorea.powerapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// 공통: 요청 후 응답용 DTO
@Data
@AllArgsConstructor(staticName = "set") // 고정된 이름으로 쓰겠다라고 정의하는 것.
public class ResponseDto<D> {

    private boolean result;
    private String message;
    private D data;

    // 요청한 것을 성공했을 때, Response
    public static <D> ResponseDto<D> setSuccess(String message, D data) {
//        return new ResponseDto<D>(true, message, data); // 이것도 가능하지만,
        return ResponseDto.set(true, message, data); // 위에서 "staticName"을 "set"으로 지정해줬기 때문에, 이렇게 사용해도 된다.
    }

    // 요청을 어떤 이유로든 실패했을 때,Response
    public static <D> ResponseDto<D> setFailed(String message) {
        return ResponseDto.set(false, message, null);
    }

}



/*
package shop.onekorea.powerapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

// 공통: 요청 후 응답용 DTO
@Data
@AllArgsConstructor(staticName = "set") // 고정된 이름으로 쓰겠다라고 정의하는 것.
public class ResponseDto<D> {

//    private boolean result;
    private int code;
//    private String token; // 2023.08.28 Conclusion. 이것은 "SignInResponseDto"에 담는다.
    private String message;
    private D data;

    // 요청한 것을 성공했을 때, Response
    public static <D> ResponseDto<D> setSuccess(String message, D data) {
        return ResponseDto.set(1, message, data);
//        return new ResponseDto<D>(true, message, data); // 이것도 가능하지만,
//        return ResponseDto.set(true, message, data); // 위에서 "staticName"을 "set"으로 지정해줬기 때문에, 이렇게 사용해도 된다.
//        return ResponseDto.set(1, message, data); // 위에서 "staticName"을 "set"으로 지정해줬기 때문에, 이렇게 사용해도 된다.
    }

    // 요청을 어떤 이유로든 실패했을 때,Response
    public static <D> ResponseDto<D> setFailed(String message) {
//    public static <D> ResponseDto<D> setFailed(String message) {
//        final int resultCode = -1;
//        final String token = null;
        return ResponseDto.set(-1, message, null);
//        return ResponseDto.set(false, message, null);
//        return ResponseDto.set(0, message, null);
    }

//    // 2023.08.27 Added. 사용자 전체 목록 리스트 가져오기.
//    public static <D> ResponseDto<D> setSuccessNoToken(String message, D data) {
//
//        return ResponseDto.set(0, "token", message, data);
//
//    }

}

*/