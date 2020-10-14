package com.ba.domain;

import com.ba.domain.abstraction.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "game")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Game extends BaseEntity {

    private String name;

    private String description;

    private long roundNo;

    private long sessionId;

    private long leagueId;

    private boolean postponed;

    private boolean played;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

}
