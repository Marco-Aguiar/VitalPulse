package med.vitalPulse.api.infra.security;

import med.vitalPulse.api.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface SecurityRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuarios where login = ?1", nativeQuery = true)
    Page<Usuario> verificarUsuario(String usuario, Pageable paginacao);
}
