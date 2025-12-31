package in.amansingh.authify.service;

import in.amansingh.authify.entity.UserEntity;
import in.amansingh.authify.io.ProfileRequest;
import in.amansingh.authify.io.ProfileResponse;
import in.amansingh.authify.repositoty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ProfileResponse createProfile(ProfileRequest request) {
        UserEntity newProfile=convertToUserEntity(request);
        if(!userRepository.existsByEmail(request.getEmail())){
            newProfile=userRepository.save(newProfile);
            return converttoProfileResponse(newProfile);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already exists");
    }

    private ProfileResponse converttoProfileResponse(UserEntity newProfile) {
       return ProfileResponse.builder()
                .name(newProfile.getName())
                .email(newProfile.getEmail())
                .userId(newProfile.getUserId())
                .isAccountVerified(newProfile.getIsAccountVerified())
                .build();
    }

    private UserEntity convertToUserEntity(ProfileRequest request) {
        UserEntity userEntity=new UserEntity();
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setName(request.getName());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setIsAccountVerified(false);
        userEntity.setResetOtpExpireAt(0L);
        userEntity.setVerifyOtp(null);
        userEntity.setVerifyOtpExpireAt(0L);
        userEntity.setResetOtp(null);
        return userEntity;
    }


}
