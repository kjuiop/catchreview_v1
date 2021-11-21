package io.gig.catchreview.core.domain.attach.dto;

import io.gig.catchreview.core.domain.attach.Attachment;
import io.gig.catchreview.core.domain.attach.FileType;
import io.gig.catchreview.core.domain.common.types.YnType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author : Jake
 * @date : 2021-11-22
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class AttachmentDto {

    private Long attachmentId;

    private FileType fileType;

    private String orgFilename;

    private String savedFilename;

    private String fullPath;

    private String thumbnailPath;

    private YnType deleteYn;

    private int fileSize;

    private int downloadCnt;

    private String description;

    public AttachmentDto(Attachment a) {
        this.attachmentId   = a.getId();
        this.fileType       = a.getFileType();
        this.orgFilename    = a.getOrgFilename();
        this.savedFilename  = a.getSavedFilename();
        this.fullPath       = a.getFullPath();
        this.thumbnailPath  = a.getThumbnailPath();
        this.fileSize       = a.getFileSize();
        this.downloadCnt    = a.getDownloadCnt();
        this.description    = a.getDescription();
    }

}
