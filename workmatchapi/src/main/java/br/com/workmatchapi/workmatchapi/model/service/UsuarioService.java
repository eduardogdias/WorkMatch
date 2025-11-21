package br.com.workmatchapi.workmatchapi.model.service;

import br.com.workmatchapi.workmatchapi.model.dto.request.UsuarioRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.UsuarioResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import br.com.workmatchapi.workmatchapi.model.enums.Role;
import br.com.workmatchapi.workmatchapi.model.exception.EntidadeNaoEncontrada;
import br.com.workmatchapi.workmatchapi.model.mapper.UsuarioMapper;
import br.com.workmatchapi.workmatchapi.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioMapper mapper;

    
    public List<UsuarioResponseDTO> listAll(){
        List<Usuario> entities = repository.findAll();
        return mapper.toListDTO(entities);
    }

    public Usuario findEntityById(Long id){
        Optional<Usuario> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new EntidadeNaoEncontrada("Usuário não encontrado");
        }
        return entity.get();
    }


    public UsuarioResponseDTO findById(Long id){
        return mapper.toDTO(findEntityById(id));
    }

    public UsuarioResponseDTO save(UsuarioRequestDTO dto){
        Usuario entity = mapper.toEntity(dto);

        String senhaEncriptada = new BCryptPasswordEncoder().encode(dto.senha());

        entity.setSenha(senhaEncriptada);
        entity.setRole(Role.USER);
        return mapper.toDTO(repository.save(entity));
    }

    public UsuarioResponseDTO edit(Long id, UsuarioRequestDTO dto){
        Usuario entity = findEntityById(id);
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setTelefone(dto.telefone());
        entity.setCpf(dto.cpf());
        entity.setSenha(dto.senha());

        return mapper.toDTO(repository.save(entity));
    }

    public UsuarioResponseDTO delete(Long id){
        Usuario entity = findEntityById(id);
        repository.deleteById(id);
        return mapper.toDTO(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }
}
