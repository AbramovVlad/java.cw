package spbstu.course.first.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spbstu.course.first.domain.Journal;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.web.bind.annotation.GetMapping;
import spbstu.course.first.repository.JournalRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("journal")
public class JournalController {
  private final JournalRepository journalRepository;

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<Journal> getJournalAll() {
    return StreamSupport
        .stream(journalRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @GetMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Journal getJournalById(@PathVariable("id") Journal journal) {
    return journal;
  }

  @PostMapping
  public Journal saveAuto(@RequestBody Journal journal) {
    return journalRepository.save(journal);
  }

  @PutMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Journal updateAuto(@PathVariable("id") Journal journalFromDb, @RequestBody Journal updatedJournal) {
    BeanUtils.copyProperties(updatedJournal, journalFromDb, "id");

    return journalRepository.save(journalFromDb);
  }

  @DeleteMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteAuto(@PathVariable("id") Journal journal) {
    journalRepository.delete(journal);
  }
}
