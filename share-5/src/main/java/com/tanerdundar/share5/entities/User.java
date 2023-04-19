package com.tanerdundar.share5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class User {

    private Long userId;
}
