package com.microservice.authservice.service.serviceImpl;


import com.microservice.authservice.dto.requestDto.ChangeRoleRequestDto;
import com.microservice.authservice.exception.NotFoundException;
import com.microservice.authservice.model.Role;
import com.microservice.authservice.model.User;
import com.microservice.authservice.repository.UserRepository;
import com.microservice.authservice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username)  {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));
            }
        };
    }

    @Override
    public boolean isExistUser(long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public String changeRole(ChangeRoleRequestDto changeRoleRequestDto, long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new NotFoundException("User Not Found");

        }else {
            if(changeRoleRequestDto.getRole() == 1){
                user.get().setRole(Role.ADMIN);
            }
            if(changeRoleRequestDto.getRole() == 0){
                user.get().setRole(Role.USER);
            }

            userRepository.save(user.get());
            return "Role changed "+ user.get().getName();
        }

    }
}
