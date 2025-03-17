package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;
@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
