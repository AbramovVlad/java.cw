package spbstu.course.first.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spbstu.course.first.domain.Personnel;
import spbstu.course.first.repository.PersonnelRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
@RequestMapping("personnel")
public class PersonnelController {
  private final PersonnelRepository personnelRepository;

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<Personnel> getPersonnelAll() {
    return StreamSupport.stream(personnelRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @GetMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Personnel getPersonnelById(@PathVariable("id") Personnel personnel) {
    return personnel;
  }

  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public Personnel savePersonnel(@RequestBody Personnel personnel) {
    return personnelRepository.save(personnel);
  }

  @PutMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Personnel putPersonnel(@PathVariable("id") Personnel personnelFromDb,
                                @RequestBody Personnel updatedPersonnel) {
    BeanUtils.copyProperties(updatedPersonnel, personnelFromDb, "id");

    return personnelRepository.save(personnelFromDb);
  }

  @DeleteMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteAuto(@PathVariable("id") Personnel personnel) {
    personnelRepository.delete(personnel);
  }
}
