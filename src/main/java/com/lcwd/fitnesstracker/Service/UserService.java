package com.lcwd.fitnesstracker.Service;

import com.lcwd.fitnesstracker.entities.User;
import com.lcwd.fitnesstracker.dtos.*;
import java.util.*;

public interface UserService {

    public List<UserDto> getAllUsers();
    public UserDto getUserById(Long id);
    public UserDto createUser(UserDto userDTO);
    public UserDto updateUser(Long id, UserDto userDTO);
    public void deleteUser(Long id);



}
