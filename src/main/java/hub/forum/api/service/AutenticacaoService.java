package hub.forum.api.service;

import hub.forum.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println("Tentando autenticar o usuário com email: " + username);
        return repository.findByEmail(username);

//        var usuario = repository.findByEmail(username);
//
//        if (usuario == null) {
//            System.out.println("Usuário não encontrado: " + username);
//            throw new UsernameNotFoundException("Usuário não encontrado");
//        }
//
//        System.out.println("Usuário encontrado: " + usuario.getUsername());
//        return usuario;
    }
}
