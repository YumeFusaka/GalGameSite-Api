package galgamesite.pojo.response.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserProfileResponse {
    private Long id;
    private String uin;
    private String gender;
    private String nick;
    private String card;
    private LocalDateTime joinTime;
    private Long generation;
    private String role;
}
