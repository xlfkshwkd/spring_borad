package com.simsimhi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BoardData extends BaseMember{
    @Id
    @GeneratedValue
    private Long seq;

    @Column(length = 100,nullable = false)
    private String subject;


    @Lob
    @Column(nullable = false)
    private String content;






}
