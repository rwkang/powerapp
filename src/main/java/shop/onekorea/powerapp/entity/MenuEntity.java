package shop.onekorea.powerapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.onekorea.powerapp.dto.MenuRequestDto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Menu") // 꼭 import javax.persistence.Entity; 이것으로 import.
@Table(name = "menu") // 실제 테이블 이름.
public class MenuEntity {
    @Id // 바로 아래 컬럼을 "PRIMARY KEY"로 설정.
    private String code;
    private String userId;
    private String language1;
    private String language2;
    private String language3;
    private String language4;
    private String winType;
    private String winName;
    private String winParam;
    private int isAccess;
    private String updatedUserId;
    private Date updated;
    private String createdUserId;
    private Date created;

//    Date date = new Date();

    public MenuEntity(MenuRequestDto menuRequestDto) {
        this.code = menuRequestDto.getCode();
        this.userId = menuRequestDto.getUserId();
        this.language1 = menuRequestDto.getLanguage1();
        this.language2 = menuRequestDto.getLanguage2();
        this.language3 = menuRequestDto.getLanguage3();
        this.language4 = menuRequestDto.getLanguage4();
        this.winType = menuRequestDto.getWinType();
        this.winName = menuRequestDto.getWinName();
        this.winParam = menuRequestDto.getWinParam();
        this.isAccess = menuRequestDto.getIsAccess();
        this.updatedUserId = menuRequestDto.getUpdatedUserId();
        this.updated = new Date();
        this.createdUserId = menuRequestDto.getCreatedUserId();
        this.updated = new Date();
    }

}
