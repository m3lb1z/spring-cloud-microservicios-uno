package dev.emrx.auth.web.security;

import dev.emrx.auth.domain.AuthUserDto;
import dev.emrx.auth.domain.NewUserDto;
import dev.emrx.auth.domain.RequestDto;
import dev.emrx.auth.domain.TokenDto;
import dev.emrx.auth.model.entity.AuthUser;
import dev.emrx.auth.model.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public AuthUser save(NewUserDto datos) {
        Optional<AuthUser> user = authUserRepository.findByUsername(datos.username());
        if(user.isPresent()) {
            return null;
        }
        String password = passwordEncoder.encode(datos.password());
        AuthUser authUser = AuthUser.builder()
                .username(datos.username())
                .password(password)
                .role(datos.role())
                .build();

        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto datos) {
        Optional<AuthUser> user = authUserRepository.findByUsername(datos.username());
        if(!user.isPresent()) {
            return null;
        }
        if(passwordEncoder.matches(datos.password(), user.get().getPassword())) {
            return new TokenDto(jwtProvider.createToken(user.get()));
        }

        return null;
    }

    public TokenDto validate(String token, RequestDto requestDto) {
        if(!jwtProvider.validate(token, requestDto)) {
            return null;
        }
        String username = jwtProvider.getUsernameFromToken(token);
        if(!authUserRepository.findByUsername(username).isPresent()) {
            return null;
        }
        return new TokenDto(token);
    }

}
