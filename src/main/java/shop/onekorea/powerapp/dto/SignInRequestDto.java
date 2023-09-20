package shop.onekorea.powerapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDto {

//    private String email;
    @NotBlank
    private String userid;

    @NotBlank
    private String password;

}
