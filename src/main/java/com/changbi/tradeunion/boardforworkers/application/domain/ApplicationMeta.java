package com.changbi.tradeunion.boardforworkers.application.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @ToString
@Table(name = "application_meta")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationMeta {

    @Id @GeneratedValue
    @Column(name = "meta_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private MetaType metaType;

    @Column
    private String metaValue;

    @Builder
    public ApplicationMeta(MetaType type, String value) {
        this.metaType = type;
        this.metaValue = value;
    }

    public void updateApplicationMetaValue(String newValue) {
        this.metaValue = newValue;
    }
}
