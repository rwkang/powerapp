package shop.onekorea.powerapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.onekorea.powerapp.entity.MenuEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponseDto {

    private MenuEntity menuEntity;

}
