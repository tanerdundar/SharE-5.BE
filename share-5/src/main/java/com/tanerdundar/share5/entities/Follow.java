package com.tanerdundar.share5.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="follows")
@Data
@NoArgsConstructor
public class Follow {

    @Id
    @Column(name="follow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long followId;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    @NonNull
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    @NonNull
    private User following;

    @Enumerated(EnumType.STRING)
    @Column(name="follow_statu")
    private Statu followStatu;

    @PrePersist
    public void prePersist() {
            this.followStatu= Statu.ACTIVE;
    }

}
