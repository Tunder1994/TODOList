package pl.dros.sda.todo.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.xml.ws.BindingType;
import java.time.LocalDate;

@Data
@Builder
public class Task {
  private Integer id;
  private String descrioption;
  private LocalDate date;
  private boolean done;
}
