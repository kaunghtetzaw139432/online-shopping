package bm.coder.shop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String type = "Bearer ";
    private String username;
    private String password;

    // Custom constructor (3 args)
    public LoginResponse(String token, String username, String message) {
        this.token = token;
        this.username = username;
        this.password = message; // or change 'password' field name to 'message'
    }
}

