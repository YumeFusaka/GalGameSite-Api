package galgamesite.pojo.response.user;

import lombok.Data;

@Data
public class UserLoginResponse {
    private String token;
    private String nick;
}
