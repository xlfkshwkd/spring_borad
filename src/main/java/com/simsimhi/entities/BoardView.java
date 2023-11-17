package com.simsimhi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Data
@Entity
@IdClass(BoardViewId.class)
public class BoardView {
    @Id
    private long seq;
    @Id
    private Integer uid;

}
