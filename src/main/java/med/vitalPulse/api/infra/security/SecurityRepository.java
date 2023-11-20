package med.vitalPulse.api.infra.security;

import med.vitalPulse.api.domain.medico.Medico;
import med.vitalPulse.api.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SecurityRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "INSERT INTO usuarios values (?1, ?2);", nativeQuery = true)
    Page<Medico> findAllByAtivos(String usuario, String senha, Pageable paginacao);
}
