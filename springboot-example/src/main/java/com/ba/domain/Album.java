package com.ba.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_ALBUM")
public class Album {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Min(value = 1, message = "ID 0'dan büyük olmalıdır")
  private Long id;

  private String title;

  private String url;

  private int year;

  private Boolean active;
}
