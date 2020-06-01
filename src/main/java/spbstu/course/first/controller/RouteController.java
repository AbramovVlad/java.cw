package spbstu.course.first.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spbstu.course.first.domain.Route;
import spbstu.course.first.repository.RouteRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("route")
@RequiredArgsConstructor
public class RouteController {
  private final RouteRepository routeRepository;

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<Route> getRouteAll() {
    return StreamSupport
        .stream(routeRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @GetMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Route getRouteById(@PathVariable("id") Route route) {
    return route;
  }

  @PutMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Route updateRoute(@PathVariable("id") Route routeFromDb, @RequestBody Route updatedRoute) {
    BeanUtils.copyProperties(updatedRoute, routeFromDb, "id");

    return routeRepository.save(routeFromDb);
  }

  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public Route saveRoute(@RequestBody Route route) {
    return routeRepository.save(route);
  }

  @DeleteMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteRoute(@PathVariable("id") Route route) {
    routeRepository.delete(route);
  }
}
