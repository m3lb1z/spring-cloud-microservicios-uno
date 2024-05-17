package dev.emrx.auth.web.controller;

import dev.emrx.auth.domain.AuthUserDto;
import dev.emrx.auth.domain.NewUserDto;
import dev.emrx.auth.domain.RequestDto;
import dev.emrx.auth.domain.TokenDto;
import dev.emrx.auth.model.entity.AuthUser;
import dev.emrx.auth.web.security.AuthService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto datos) {
        TokenDto token = authService.login(datos);
        if(token == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(token);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto requestDto) {
        TokenDto tokenDto = authService.validate(token, requestDto);

        if(tokenDto == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody NewUserDto datos) {
        AuthUser user = authService.save(datos);

        if(user == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user);
    }


}
