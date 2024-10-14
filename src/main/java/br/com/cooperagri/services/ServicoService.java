package br.com.cooperagri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cooperagri.model.Servico;
import br.com.cooperagri.repositories.ServicoRepository;
import br.com.cooperagri.services.exceptions.ResouceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Transactional(readOnly = true)
    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Servico findById(Long id) {
        var servicoLocalizado = servicoRepository.findById(id);
        return servicoLocalizado.orElseThrow(() -> new ResouceNotFoundException(id));

    }

    @Transactional
    public Servico create(Servico servico) {
        return servicoRepository.save(servico);
    }

    @Transactional
    public void delete(Long id) {
        try {
            servicoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

            throw new ResouceNotFoundException(id);
        }
    }

    @Transactional
    public Servico update(Long id, Servico servico) {
        try {
            Servico aux = servicoRepository.getReferenceById(id);
            updateData(aux, servico);
            return servicoRepository.save(aux);
        } catch (EntityNotFoundException e) {
            throw new ResouceNotFoundException(id);
        }
    }

    private void updateData(Servico aux, Servico servico) {

        Optional.ofNullable(servico.getFuncionarios()).ifPresent(funcionario -> aux.setFuncionarios(funcionario));
        Optional.ofNullable(servico.getServicoCode()).ifPresent(aux::setServicoCode);
        Optional.ofNullable(servico.getValorServico()).ifPresent(aux::setValorServico);
        Optional.ofNullable(servico.getQuantidadeDeHoras()).ifPresent(aux::setQuantidadeDeHoras);
        Optional.ofNullable(servico.getDia()).ifPresent(aux::setDia);
    }

}
