package spbstu.course.first.domain;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Journal {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private LocalDateTime timeout;

  private LocalDateTime timein;

  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Auto auto;

  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Route route;
}
