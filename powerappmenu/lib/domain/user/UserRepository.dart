// 2023.08.06 Conclusion. Provider 역활: 통신이고, Repository 역활은 통신을 호출해서, 예쁘게 가공하는 일!!!
// 왜 가공해야 하느냐면, JSON => Dart Object, 역변환을 해야 하기 때문이다.

import 'dart:convert';

import 'package:get/get_connect/http/src/response/response.dart';
import 'package:powerappmenu/domain/user/User.dart';
import 'package:powerappmenu/domain/user/UserProvider.dart';
import 'package:powerappmenu/dto/ResponseDto.dart';
import 'package:powerappmenu/util/Jwt.dart';

import '../../dto/LoginRequestDto.dart';


class UserRepository {

  final UserProvider _userProvider = UserProvider();

  /// 2023.08.13 Conclusion. User 정보까지 받아 와서, 이것을 "상태 관리" 하면서,
  /// "내 게시글"일 경우, "수정" 및 "삭제" 가능하도록 처리.
  Future<User> loginRepository(String username, String password) async {
  // Future<String> loginRepository(String username, String password) async { /// 일단 user 정보 삐고, "토큰"만 받아 오기
  // Future<Response> login(String username, String password) async {
  // Future<void> login(String username, String password) async {

    // 상기, (String username, String password)는 Map 타입이 아니므로, 반드시 Map 타입으로 형 변경하여,
    // 아래 data에 실어 보낸다.

    LoginRequestDto loginRequestDto = LoginRequestDto(username: username, password: password);

    // 위에서 처럼, "dart Object"인, "LoginRequestDto"를 JSON 형식으로 변환하는 것은,
    // 아래와 같이, ".toJson()" 메서드를 호출하기만 하면 된다.
    // 그러기 위해서, "dto" Object를 만드는 것이다.

    Response response = await _userProvider.loginProvider(loginRequestDto.toJson());

    print("loginRequestDto.toJson(): ${loginRequestDto.toJson()}"); // {username: ssar, password: 1234} : 아래와 똑 같이 뿌려진다
    print("loginRequestDto.toJson().toString(): ${loginRequestDto.toJson().toString()}"); // {username: ssar, password: 1234}
    print("=====================================");
    print("response: $response"); // Instance of 'Response<dynamic>'
    print("=====================================");
    print("response.body: ${response.body}"); // {code: 1, msg: success, data: {id: 1, username: ssar, password: null, email: ssar@nate.com, created: 2023-08-07T18:09:38, updated: 2023-08-07T18:09:38}}
    print("=====================================");
    print("response.headers: ${response.headers}"); // {content-type: application/json;charset=utf-8, date: Mon, 07 Aug 2023 09:17:27 GMT, transfer-encoding: chunked, authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb3PthqDtgbAiLCJpZCI6MSwiZXhwIjoxNjkxNDAzNDQ3fQ.J1FbnoFh2asF66cCex35H-LEZ0zOKfdj06L-hQP2HPIQRsefGLZSD1-1EDEOhOXzialk5AWIk1aiZ2RdQfeMHQ}
    print("=====================================");

    //["code"] != 1) { // body 자체 값이, text.텍스트 값으로, "인증 되지 않았습니다. 다시 인증해주세요." 이므로,
    // headers["content-type"] = "text/plain" 이것으로 분기한다.

    dynamic headers = response.headers;
    String headersContentType = headers["content-type"];
    print("contentType.substring(11): ${headersContentType.substring(0, 11)}");

    /// 2023.08.10 Added. 사용자 정보 또한 "상태 관리"에 Obs()로 올려서 관리하면서,
    /// "내 게시글"일 경우에만, "수정", "삭제" 하게 한다.
    dynamic body = response.body;
    print("/repository/UserRepository.java/body: ${body}");
    print("1========================================================");

    /// 한글 변환은 필요 없다.
    /// dynamic convertBody = convertUtf8ToObject(body);

    /// 2023.08.13 Conclusion. User 정보까지 받아 와서, 이것을 "상태 관리" 하면서,
    /// "내 게시글"일 경우, "수정" 및 "삭제" 가능하도록 처리.
    ResponseDto responseDto = ResponseDto.fromJson(body);
    if (responseDto.code == 1) {
      User principal = User.fromJson(responseDto.data); /// principal: 앞장서는(leading), 주요한,... 즉, "인증 정보"
      // User user = User.fromJson(responseDto.data);

      String token = headers["authorization"];
      // if (token!= "-1") {
        // Login.로그인 성공시, 상태 관리 변경... RxBool 타입은 꼭 ".value" 형식으로 사용.
        // isLogin.value = true;
        jwtToken = token;
      // } else {
      //   // 여기는 token 값이 null 이다.
      // }

      return principal;
    } else {
      // User principal = new User();
      return User();
    }


    // /// 일단 user 정보 삐고, "토큰"만 받아 오기
    // if (headers["authorization"] == null) {
    // // if (headersContentType.substring(0, 11) != "application") {
    //   print("/repository/UserRepository.java/=====> 로그인 실패: ${headers["authorization"]}");
    //   String token = "";
    //   return "-1";
    // } else {
    //   print("/repository/UserRepository.java/=====> 로그인 성공");
    // }

    // String token = headers["authorization"];
    // dynamic headers = response.headers;

    // print("=====================================");
    // print("token: ${token}"); // Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb3PthqDtgbAiLCJpZCI6MSwiZXhwIjoxNjkxNDA2MDgzfQ.Uqn2EDU5lQDIWuDKD9RrGfeh4RiPRhDWMbgwY3dAqyRRvcpybaVF9Vj7viFYxSA57Xp56jrgen8mhXCUinhjVA

    // 2023.08.07 Conclusion. "token"을 아래와 같이 받게 되면, "String?" 그리고 ".headers!" 이런 식으로 받아야 됨으로,
    // 위와 같이, 먼저 "dynamic" 타입으로 "headers"를 "dynamic" 타입으로 선언하면서 그 값을 받게 하면,
    // "?" 그리 "!" 이것을 빼도 에러가 발생하지 않는다.

    // String? token = response.headers!["authorization"];

    // return token;

  }

}