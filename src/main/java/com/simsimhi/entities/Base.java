package com.simsimhi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class Base {

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedAt;



}
