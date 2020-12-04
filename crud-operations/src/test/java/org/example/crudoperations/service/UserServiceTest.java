package org.example.crudoperations.service;


import org.example.crudoperations.entity.Role;
import org.example.crudoperations.entity.User;
import org.example.crudoperations.exceptions.UserAlreadyExistException;
import org.example.crudoperations.repository.RoleRepository;
import org.example.crudoperations.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.UUID;

import java.util.Arrays;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    private UserRepository repository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;

    User testUser;
    Role testRole;
    @Before
    public void setup(){
        testRole=new Role("ROLE_USER");
        testUser=new User("ankita098","ankita@gmail.com","abcdf",Arrays.asList(testRole));

    }
    @Test
    public void testLoadUserByUsername() {
        String userId="ankita098";
        Mockito.when(repository.findByUserId(Mockito.eq(userId))).thenReturn(Optional.of(testUser));
        UserDetails userDetails= userService.loadUserByUsername(userId);
        Mockito.verify(repository).findByUserId(userId);
        Assert.assertEquals(testUser.getPassword(),userDetails.getPassword());

    }
    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameThrowException() {
        String userId="ankita098";
        Mockito.when(repository.findByUserId(Mockito.eq(userId))).thenReturn(Optional.empty());
        UserDetails userDetails= userService.loadUserByUsername(userId);
        Mockito.verify(repository).findByUserId(userId);

    }
    @Test
    public void testSaveNewUser() {
        String userId="ankita098";
        Mockito.when(repository.findByUserId(Mockito.eq(userId))).thenReturn(Optional.empty());
        Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(testUser);
        User savedUser= userService.save(testUser);
        Assert.assertEquals(testUser,savedUser);
        Mockito.verify(repository).findByUserId(userId);
        Mockito.verify(passwordEncoder,Mockito.times(1)).encode(testUser.getPassword());
        Mockito.verify(roleRepository).findByName("ROLE_USER");
    }
    @Test(expected = UserAlreadyExistException.class)
    public void testSaveNewUserThrowException() {
        String userId="ankita098";
        Mockito.when(repository.findByUserId(Mockito.eq(userId))).thenReturn(Optional.of(testUser));
        userService.save(testUser);

        Mockito.verify(repository).findByUserId(userId);
        Mockito.verify(passwordEncoder,Mockito.times(0)).encode(testUser.getPassword());
    }




}
