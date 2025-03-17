package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;

@Data
public class AanmeldenAanvraag {

    private String email;    // Field to store the email address of the user.
    private String password; // Field to store the password provided by the user.
    private String name;     // Field to store the name of the user.
}
