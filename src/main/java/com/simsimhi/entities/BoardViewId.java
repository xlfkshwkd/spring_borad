package com.simsimhi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BoardViewId {
    @Id
    private Long seq;
    @Id
    @Column(name = "_uid")
    private Integer uid;

}
