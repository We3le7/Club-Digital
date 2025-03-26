package com.club.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//添加活动
@Data
@Getter
@Setter
public class FileUploadDTO {
    private List<FileUrl> fileUrls;
    private Integer clubId;

    // getters and setters

    public static class FileUrl {
        private String url;
        // getters and setters
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }
}
