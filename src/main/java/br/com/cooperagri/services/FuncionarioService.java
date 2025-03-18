/*
 * package br.com.cooperagri.services;
 * 
 * import java.util.List;
 * import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.dao.EmptyResultDataAccessException;
 * import org.springframework.stereotype.Service;
 * 
 * import br.com.cooperagri.model.Funcionario;
 * import br.com.cooperagri.repositories.FuncionarioRepository;
 * import br.com.cooperagri.services.exceptions.ResouceNotFoundException;
 * import jakarta.persistence.EntityNotFoundException;
 * 
 * @Service
 * public class FuncionarioService {
 * 
 * @Autowired
 * private FuncionarioRepository funcionarioRepository;
 * 
 * public List<Funcionario> findAll(){
 * return funcionarioRepository.findAll();
 * }
 * 
 * public Funcionario findById(Long id){
 * var funcionarioLocalizado = funcionarioRepository.findById(id);
 * return funcionarioLocalizado.orElseThrow(() -> new
 * ResouceNotFoundException(id));
 * 
 * }
 * 
 * public Funcionario create(Funcionario funcionario) {
 * return funcionarioRepository.save(funcionario);
 * }
 * 
 * public void delete(Long id) {
 * try {
 * funcionarioRepository.deleteById(id);
 * } catch (EmptyResultDataAccessException e) {
 * 
 * throw new ResouceNotFoundException(id);
 * }
 * }
 * 
 * public Funcionario update(Long id, Funcionario funcionario) {
 * try {
 * Funcionario aux = funcionarioRepository.getReferenceById(id);
 * updateData(aux, funcionario);
 * return funcionarioRepository.save(aux);
 * } catch (EntityNotFoundException e) {
 * throw new ResouceNotFoundException(id);
 * }
 * }
 * 
 * private void updateData(Funcionario aux, Funcionario funcionario) {
 * Optional.ofNullable(funcionario.getCpf()).ifPresent(aux::setCpf);
 * Optional.ofNullable(funcionario.getRg()).ifPresent(aux::setRg);
 * Optional.ofNullable(funcionario.getNome()).ifPresent(aux::setNome);
 * Optional.ofNullable(funcionario.getEndereco()).ifPresent(aux::setEndereco);
 * Optional.ofNullable(funcionario.getTelefone()).ifPresent(aux::setTelefone);
 * // Optional.ofNullable(funcionario.getDados_bancarios()).ifPresent(aux::
 * setDados_bancarios);
 * 
 * }
 * }
 */
