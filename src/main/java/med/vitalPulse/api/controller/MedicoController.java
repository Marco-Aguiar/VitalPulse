package med.vitalPulse.api.controller;

import com.auth0.jwt.exceptions.JWTCreationException;
import jakarta.validation.Valid;
import med.vitalPulse.api.domain.medico.DadosListagemMedico;
import med.vitalPulse.api.domain.medico.Medico;
import med.vitalPulse.api.domain.medico.MedicoRepository;
import med.vitalPulse.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.SQLException;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping("/nome")
    public ResponseEntity<Page<DadosListagemMedico>> listarNomes(@RequestBody @Valid NomeMedicoConsulta nome, @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
            var page = repository.findAllByNome(nome.nome(), paginacao).map(DadosListagemMedico::new);
            return ResponseEntity.ok(page);
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<DadosListagemMedico>> listarAtivos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivos(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<DadosListagemMedico>> listarInativos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByInativos(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

}
