package med.vitalPulse.api.controller;

import jakarta.validation.Valid;
import med.vitalPulse.api.domain.usuario.DadosAutenticacao;
import med.vitalPulse.api.domain.usuario.Usuario;
import med.vitalPulse.api.infra.security.DadosTokenJWT;
import med.vitalPulse.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/criar")
    public ResponseEntity criarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        System.out.println("Caiu aqui");
        System.out.println(dados.senha());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        var senhaCriptografada  = bCryptPasswordEncoder.encode(dados.senha());
        System.out.println(senhaCriptografada);

        return ResponseEntity.ok().build();
    }

}
