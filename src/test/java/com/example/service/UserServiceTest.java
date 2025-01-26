package com.example.service;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.enums.RoleName;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserService();
        userService.userRepository = userRepository;
        userService.roleRepository = roleRepository;
        userService.passwordEncoder = passwordEncoder;
    }
    @Test
    void testRegisterUser_Success() {
        String username = "testuser";
        String rawPassword = "password123";
        String encodedPassword = "encodedPassword123";
        Role userRole = new Role();
        userRole.setId(1L);
        userRole.setName(RoleName.ROLE_USER);
        when(roleRepository.findByName(RoleName.ROLE_USER)).thenReturn(Optional.of(userRole));
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);
        when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        User registeredUser = userService.registerUser(username, rawPassword);
        assertNotNull(registeredUser);
        assertEquals(username, registeredUser.getUsername());
        assertEquals(encodedPassword, registeredUser.getPassword());
        assertEquals(1, registeredUser.getRoles().size());
        assertTrue(registeredUser.getRoles().contains(userRole));
        verify(roleRepository, times(1)).findByName(RoleName.ROLE_USER);
        verify(passwordEncoder, times(1)).encode(rawPassword);
        verify(userRepository, times(1)).save(Mockito.any(User.class));
    }
    @Test
    void testRegisterUser_RoleNotFound() {
        String username = "testuser";
        String rawPassword = "password123";
        when(roleRepository.findByName(RoleName.ROLE_USER)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(username, rawPassword);
        });
        assertEquals("Role not found", exception.getMessage());
        verify(roleRepository, times(1)).findByName(RoleName.ROLE_USER);
        verify(userRepository, never()).save(Mockito.any(User.class));
    }
}