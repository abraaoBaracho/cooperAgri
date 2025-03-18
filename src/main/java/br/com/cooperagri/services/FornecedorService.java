package br.com.cooperagri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.cooperagri.model.Fornecedor;
import br.com.cooperagri.repositories.FornecedorRepository;
import br.com.cooperagri.services.exceptions.ResouceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor findById(Long id) {
        var fornecedorLocalizado = fornecedorRepository.findById(id);
        return fornecedorLocalizado.orElseThrow(() -> new ResouceNotFoundException(id));

    }

    public Fornecedor create(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public void delete(Long id) {
        try {
            fornecedorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

            throw new ResouceNotFoundException(id);
        }
    }

    public Fornecedor update(Long id, Fornecedor fornecedor) {
        try {
            Fornecedor aux = fornecedorRepository.getReferenceById(id);
            updateData(aux, fornecedor);
            return fornecedorRepository.save(aux);
        } catch (EntityNotFoundException e) {
            throw new ResouceNotFoundException(id);
        }
    }

    private void updateData(Fornecedor aux, Fornecedor fornecedor) {
        Optional.ofNullable(fornecedor.getCpf()).ifPresent(aux::setCpf);
        Optional.ofNullable(fornecedor.getRg()).ifPresent(aux::setRg);
        Optional.ofNullable(fornecedor.getNome()).ifPresent(aux::setNome);
        Optional.ofNullable(fornecedor.getEndereco()).ifPresent(aux::setEndereco);
        Optional.ofNullable(fornecedor.getTelefone()).ifPresent(aux::setTelefone);
       // Optional.ofNullable(fornecedor.getDados_bancarios()).ifPresent(aux::setDados_bancarios);
        Optional.ofNullable(fornecedor.getCaf_dap()).ifPresent(aux::setCaf_dap);
        Optional.ofNullable(fornecedor.getApelido()).ifPresent(aux::setApelido);
    }


}
