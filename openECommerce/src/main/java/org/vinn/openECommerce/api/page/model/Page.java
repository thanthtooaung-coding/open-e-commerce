package org.vinn.openECommerce.api.page.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.vinn.openECommerce.api.user.pageOwner.model.PageOwner;

import java.time.Instant;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @Column(nullable = false ,length = 20)
    private String shortName;

    @Column(length = 100)
    private String url;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(insertable = false)
    @LastModifiedDate
    private Instant updatedAt;

    @Column(nullable = false)
    private boolean active;

    @OneToOne
    @JoinColumn(name = "page_owner_id")
    private PageOwner pageOwner;
}
