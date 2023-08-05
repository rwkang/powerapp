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

    public ResponseDto<PatchUserResponseDto> patchUser(PatchUserRequestDto requestDto, String email) {

        UserEntity userEntity = null;
        String nickname = requestDto.getNickname();
        String profile = requestDto.getProfile();

        try {
            userEntity = userRepository.findByEmail(email);
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
