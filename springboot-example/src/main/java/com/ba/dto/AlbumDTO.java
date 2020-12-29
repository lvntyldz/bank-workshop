package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTO {
  @NotNull(message = "Id alanı zorunludur")
  //@Min(value = 1, message = "ID 1'den küçük olamaz")
  private Long id;

  @NotNull(message = "title alanı zorunludur")
  private String title;

  @NotNull(message = "URL bilgisi zorunludur")
  private String url;

  @Min(value = 1900, message = "Yıl bilgisi 1900'den küçük olamaz")
  @Max(value = 2020, message = "Yıl bilgisi 2020'den büyük olamaz")
  @NotNull(message = "Yıl alanı zorunludur")
  private int year;

  @NotNull(message = "Durum bilgisi zorunludur")
  private Boolean active;
}
