package lk.ijse.DTO.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserTM {
    private String userId;
    private String userName;
    private String password;
    private String role;
}
