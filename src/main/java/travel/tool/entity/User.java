package travel.tool.entity;

import lombok.*;

/**
 * @author ipop
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String photo;
}
