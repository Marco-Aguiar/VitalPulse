package med.vitalPulse.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {


    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :idMedico
            """)
    Boolean findAtivoById(Long idMedico);




    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = "SELECT * FROM medicos WHERE nome = ?1", nativeQuery = true)
    Page<Medico> findAllByNome(String nome, Pageable paginacao);

    @Query(value = "SELECT * FROM medicos WHERE ativo = '1'", nativeQuery = true)
    Page<Medico> findAllByAtivos(Pageable paginacao);

    @Query(value = "SELECT * FROM medicos WHERE ativo = '0'", nativeQuery = true)
    Page<Medico> findAllByInativos(Pageable paginacao);

    @Query("""
    SELECT m FROM Medico m where m.ativo = true and
    m.especialidade = :especialidade
    and m.id not in(
        SELECT c.medico.id FROM Consulta c where c.data = :data
    )
    order by rand()
    limit 1
    """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}
