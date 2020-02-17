package io.medalytics.projectactions.Service;

import io.medalytics.projectactions.Models.CustomUserDetails;
import io.medalytics.projectactions.Models.Users;
import io.medalytics.projectactions.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findUsersByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username "+username+" not found"));
        return CustomUserDetails.create(user);
    }

    public UserDetails loadUserById(long id) throws UsernameNotFoundException {
        Users user = userRepository.findUsersById(id)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        return CustomUserDetails.create(user);
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public void saveUser(Users users){
        userRepository.save(users);
    }
}
