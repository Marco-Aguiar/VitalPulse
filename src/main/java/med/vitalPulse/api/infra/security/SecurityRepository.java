package med.vitalpulse.api.infra.security;

import med.vitalpulse.api.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SecurityRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuarios where login = ?1", nativeQuery = true)
    Page<Usuario> verificarUsuario(String usuario, Pageable paginacao);
}
