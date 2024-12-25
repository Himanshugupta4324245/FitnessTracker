package com.lcwd.fitnesstracker.service;


import com.lcwd.fitnesstracker.Service.UserService;
import com.lcwd.fitnesstracker.dtos.UserDto;
import com.lcwd.fitnesstracker.entities.User;
import com.lcwd.fitnesstracker.exceptions.ResourceNotFoundException;
import com.lcwd.fitnesstracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDto userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a sample user and DTO
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole(User.Role.USER);

        userDTO = new UserDto();
        userDTO.setId(1L);
        userDTO.setUsername("testuser");
        userDTO.setPassword("password");
        userDTO.setRole(User.Role.USER);
    }

    @Test
    void testGetUserById_Success() {
        // Mock repository behavior
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Call the service method
        UserDto result = userService.getUserById(1L);

        // Verify the result
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());
    }

    @Test
    void testGetUserById_NotFound() {
        // Mock repository behavior
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method and expect an exception
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void testCreateUser_Success() {
        // Mock repository behavior
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Call the service method
        UserDto result = userService.createUser(userDTO);

        // Verify the result
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());
    }

    @Test
    void testUpdateUser_Success() {
        // Mock repository behavior
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Call the service method
        UserDto result = userService.updateUser(1L, userDTO);

        // Verify the result
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());
    }

    @Test
    void testUpdateUser_NotFound() {
        // Mock repository behavior
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method and expect an exception
        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(1L, userDTO));
    }

    @Test
    void testDeleteUser_Success() {
        // Mock repository behavior
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Call the service method
        assertDoesNotThrow(() -> userService.deleteUser(1L));

        // Verify that the delete method was called
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testDeleteUser_NotFound() {
        // Mock repository behavior
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method and expect an exception
        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(1L));
    }


}
