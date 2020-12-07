package org.example.crudoperations.service;

import org.example.crudoperations.entity.Role;
import org.example.crudoperations.entity.User;
import org.example.crudoperations.exceptions.UserAlreadyExistException;
import org.example.crudoperations.repository.RoleRepository;
import org.example.crudoperations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String userId)  {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        User user=userOptional.get();
        UserDetails userDetails=  new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), mapRolesToAuthorities(user.getRoles())); //NOSONAR
        return userDetails;
    }
    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User save(User signedUpUser) {
        if (isUserExist(signedUpUser)) {
            throw new UserAlreadyExistException();
        }
        User user = new User(signedUpUser.getUserId(),signedUpUser.getEmail(),
                passwordEncoder.encode(signedUpUser.getPassword()), Arrays.asList(getRole("ROLE_USER")));

        return userRepository.save(user);
    }
    private boolean isUserExist(User signedUpUser){
        Optional<User> userOptional=userRepository.findByUserId(signedUpUser.getUserId());
        return userOptional.isPresent();
    }
    private Role getRole(String role){
        Optional<Role> roleOptional= roleRepository.findByName(role);
        if(roleOptional.isPresent()){
            return roleOptional.get();
        }
        else {
           return new Role(role);
        }
    }

}
