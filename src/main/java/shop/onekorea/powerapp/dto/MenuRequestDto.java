package shop.onekorea.powerapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequestDto { // 회원 가입

    private String code;
    private String userid;
    private String menu1;
    private String menu2;
    private String menu3;
    private String menu4;
    private String language1;
    private String language2;
    private String language3;
    private String language4;
    private String winType;
    private String winName;
    private String winParam;
    private int isAccess;
    private String updatedUserid; // 이게 여기 DTO에서 필요한가???
    private String createdUserid; // 이게 여기 DTO에서 필요한가???

}
