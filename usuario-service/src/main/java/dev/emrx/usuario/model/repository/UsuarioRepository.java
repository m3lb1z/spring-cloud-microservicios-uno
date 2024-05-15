package dev.emrx.usuario.model.repository;

import dev.emrx.usuario.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
