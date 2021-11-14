package io.gig.catchreview.core.domain.attach;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : Jake
 * @date : 2021-09-22
 */
@Getter
@RequiredArgsConstructor
public enum FileType {

    Image("image", "이미지"),
    Video("video", "동영상"),
    Document("document", "문서");

    final private String type;
    final private String description;
}
