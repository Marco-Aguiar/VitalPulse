package med.vitalPulse.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = "SELECT * FROM medicos WHERE nome = ?1", nativeQuery = true)
    Page<Medico> findAllByNome(String nome, Pageable paginacao);

    @Query(value = "SELECT * FROM medicos WHERE ativo = '1'", nativeQuery = true)
    Page<Medico> findAllByAtivos(Pageable paginacao);

    @Query(value = "SELECT * FROM medicos WHERE ativo = '0'", nativeQuery = true)
    Page<Medico> findAllByInativos(Pageable paginacao);
}
