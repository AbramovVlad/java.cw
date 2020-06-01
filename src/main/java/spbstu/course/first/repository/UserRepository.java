package spbstu.course.first.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spbstu.course.first.auth.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String name);
}
