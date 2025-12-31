package in.amansingh.authify.service;

import in.amansingh.authify.io.ProfileRequest;
import in.amansingh.authify.io.ProfileResponse;

public interface ProfileService {
    ProfileResponse createProfile(ProfileRequest request);
}
