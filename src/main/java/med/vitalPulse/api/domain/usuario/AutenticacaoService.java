package med.vitalpulse.api.domain.usuario;

import med.vitalpulse.api.domain.ValidacaoException;
import med.vitalpulse.api.infra.security.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    SecurityRepository securityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public Usuario criarLogin(String login, String senha) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        var senhaCriptografada  = bCryptPasswordEncoder.encode(senha);
        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(login);
        novoUsuario.setSenha(senhaCriptografada);
        if(repository.existsByLogin(novoUsuario.getLogin())){
            throw new ValidacaoException("Este nome de usuário já existe!");
        }
        securityRepository.save(novoUsuario);
        return novoUsuario;
    }
}
