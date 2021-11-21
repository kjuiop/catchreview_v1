package io.gig.catchreview.core.domain.attach;

import io.gig.catchreview.core.domain.common.types.YnType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * @author : Jake
 * @date : 2021-11-22
 */
@Entity
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private FileType fileType;

    @Column(length = 1000)
    private String orgFilename;

    @Column(length = 1000)
    private String savedFilename;

    @Column(length = 1000)
    private String fullPath;

    @Column(length = 1000)
    private String thumbnailPath;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private YnType deleteYn = YnType.N;

    @Column
    private int fileSize;

    @Column
    private int downloadCnt;

    @Lob
    private String description;
}
