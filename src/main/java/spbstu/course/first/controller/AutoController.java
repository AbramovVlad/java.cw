package spbstu.course.first.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spbstu.course.first.domain.Auto;
import spbstu.course.first.repository.AutoRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("auto")
@RequiredArgsConstructor
public class AutoController {
  private final AutoRepository autoRepository;

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<Auto> getAutoAll() {
    return StreamSupport.stream(autoRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @PutMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Auto putAuto(@PathVariable("id") Auto autoFromDb, @RequestBody Auto updatedAuto) {
    BeanUtils.copyProperties(updatedAuto, autoFromDb, "id");

    return autoRepository.save(autoFromDb);
  }

  @GetMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Auto getAutoById(@PathVariable("id") Auto auto) {
    return auto;
  }

  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public Auto saveAuto(@RequestBody Auto auto) {
    return autoRepository.save(auto);
  }

  @DeleteMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteAuto(@PathVariable("id") Auto auto) {
    autoRepository.delete(auto);
  }
}
