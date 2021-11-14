package io.gig.catchreview.core.domain.attach;

import com.google.common.base.Strings;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author : Jake
 * @date : 2021-09-22
 */
public class FileUtils {

    /* Not Allowed File Extension */
    public static final String FILE_PERMIT_EXTENSION = "hwp,pdf,xls,xlsx,doc,docx,ppt,pptx,zip,alz,jpg,jpeg,png,txt,log,gif";

    /* Image File Extension */
    public static final String IMAGE_FILE_EXTENSION = "bmp,jpg,jpeg,gif,png,psd,ai,pic";

    /* Audio File Extension */
    public static final String VIDEO_FILE_EXTENSION = "mp3,mp4,ogg,wma,wav,au,rm,mid";

    public static boolean permitExtensionCheck(String originalFileName) {
        if (Strings.isNullOrEmpty(originalFileName)) return false;

        String fileExtension = FilenameUtils.getExtension(originalFileName);

        if (Arrays.asList(StringUtils.commaDelimitedListToStringArray(FILE_PERMIT_EXTENSION.trim().toLowerCase())).contains(fileExtension.trim().toLowerCase())) {
            return true;
        }

        if (Arrays.asList(StringUtils.commaDelimitedListToStringArray(VIDEO_FILE_EXTENSION.trim().toLowerCase())).contains(fileExtension.trim().toLowerCase())) {
            return true;
        }

        return false;
    }

    public static FileType defineMediaType(String fileExtension) {
        FileType fileType = FileType.Document;

        if (Arrays.asList(StringUtils.commaDelimitedListToStringArray(IMAGE_FILE_EXTENSION.trim().toLowerCase())).contains(fileExtension.trim().toLowerCase())) {
            fileType = FileType.Image;
        }

        if (Arrays.asList(StringUtils.commaDelimitedListToStringArray(VIDEO_FILE_EXTENSION.trim().toLowerCase())).contains(fileExtension.trim().toLowerCase())) {
            fileType = FileType.Video;
        }

        return fileType;
    }

}
