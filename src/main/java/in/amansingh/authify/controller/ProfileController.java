package in.amansingh.authify.controller;

import in.amansingh.authify.io.ProfileRequest;
import in.amansingh.authify.io.ProfileResponse;
import in.amansingh.authify.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService  profileService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse register(@Valid  @RequestBody ProfileRequest request){
       ProfileResponse response= profileService.createProfile(request);
       //TODO: send welcome email
        return response;
    }

}
