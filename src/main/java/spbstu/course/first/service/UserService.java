package spbstu.course.first.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spbstu.course.first.auth.User;
import spbstu.course.first.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return userRepository.findByUsername(s);
  }

  public User save(User user) {
    return userRepository.save(user);
  }
}
