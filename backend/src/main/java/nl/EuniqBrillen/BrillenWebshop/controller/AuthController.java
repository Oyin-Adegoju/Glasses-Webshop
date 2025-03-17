package nl.EuniqBrillen.BrillenWebshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.diensten.jwt.auth.AuthServices;
import nl.EuniqBrillen.BrillenWebshop.dto.AanmeldenAanvraag;
import nl.EuniqBrillen.BrillenWebshop.dto.AuthenticationRequest;
import nl.EuniqBrillen.BrillenWebshop.dto.GebruikerDto;
import nl.EuniqBrillen.BrillenWebshop.dto.WachtwoordVeranderenDto;
import nl.EuniqBrillen.BrillenWebshop.entity.Gebruiker;
import nl.EuniqBrillen.BrillenWebshop.repository.UserRepository;
import nl.EuniqBrillen.BrillenWebshop.utils.JwtUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {
    //Dependencies for authenticating
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    private final AuthServices authServices;


    @PostMapping({"/sign-up"})
    public ResponseEntity<?> signupUser(@RequestBody(required = true) AanmeldenAanvraag aanmeldenAanvraag)
            throws Exception {

        if (authServices.hasUserWithEmail(aanmeldenAanvraag.getEmail()))
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);

        GebruikerDto createdUser = authServices.createUser(aanmeldenAanvraag);
        if (createdUser == null)
            return new ResponseEntity<>("User not created, come again later", HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException, JSONException, ServletException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password.");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return;
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Optional<Gebruiker> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        if (optionalUser.isPresent()) {
            response.getWriter().write(new JSONObject()
                    .put("userId", optionalUser.get().getId())
                    .put("role", optionalUser.get().getRole())
                    .toString()
            );
            response.addHeader("Access-Control-Expose-Headers", "Authorization");
            response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
            response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
        }
    }

    @PostMapping("/api/update")
    public ResponseEntity<GebruikerDto> updateProfile(@ModelAttribute GebruikerDto gebruikerDto) throws IOException {
        GebruikerDto updatedUser = authServices.updateUser(gebruikerDto);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/user/{userId}")
    public ResponseEntity<GebruikerDto> getUserById(@PathVariable Long userId) {
        GebruikerDto userDto = authServices.getUserById(userId);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody WachtwoordVeranderenDto wachtwoordVeranderenDto) {
        try {
            return authServices.updatePasswordById(wachtwoordVeranderenDto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Iets ging fout. Probeer aub opnieuw");
        }
    }


}
