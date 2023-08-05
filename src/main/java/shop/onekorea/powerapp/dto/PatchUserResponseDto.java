package shop.onekorea.powerapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.onekorea.powerapp.entity.UserEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchUserResponseDto {

    private UserEntity user;

}
