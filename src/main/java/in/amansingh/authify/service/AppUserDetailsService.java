package in.amansingh.authify.service;

import in.amansingh.authify.entity.UserEntity;
import in.amansingh.authify.repositoty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final  UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       UserEntity existingUser= userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("Email not found for the email:" +email));
       return new User(existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());
    }
}
