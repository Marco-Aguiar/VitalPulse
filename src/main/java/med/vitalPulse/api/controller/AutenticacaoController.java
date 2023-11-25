package med.vitalpulse.api.controller;

import jakarta.validation.Valid;
import med.vitalpulse.api.domain.usuario.AutenticacaoService;
import med.vitalpulse.api.domain.usuario.DadosAutenticacao;
import med.vitalpulse.api.domain.usuario.Usuario;
import med.vitalpulse.api.infra.security.DadosTokenJWT;
import med.vitalpulse.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/criar")
    public ResponseEntity criarLogin(@RequestBody @Valid DadosAutenticacao dados, @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        autenticacaoService.criarLogin(dados.login(), dados.senha());
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

}
