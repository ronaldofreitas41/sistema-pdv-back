package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.count.Count;
import com.pdvsystem.api.domain.count.CountRequestDTO;
import com.pdvsystem.api.repositories.CountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CountService {

    @Autowired
    private CountRepository countRepository;

    //Contas a Receber

    /*
     * Cria Conta a Receber
     */
    public Count createCountRecieve(CountRequestDTO data) {
        Count count = new Count();

        count.setCategoria(data.categoria());
        count.setDescricao(data.descricao());
        count.setValor(data.valor());
        count.setVencimento(data.vencimento());
        count.setStatus(data.status());
        count.setType("RECIEVE");

        return countRepository.save(count);
    }

    /*
     * Busca todas as Contas a Receber
     */
    public List<Count> getAllCountRecieve() {
        return countRepository.findByType("RECIEVE");
    }


    //------------------------------------------------------------------------------------------------------------------
    //Contas a Pagar

    /*
     * Cria Conta a Pagar
     */
    public Count createCountPay(CountRequestDTO data) {
        Count count = new Count();

        count.setCategoria(data.categoria());
        count.setDescricao(data.descricao());
        count.setValor(data.valor());
        count.setVencimento(data.vencimento());
        count.setStatus(data.status());
        count.setType("PAY");

        return countRepository.save(count);
    }

    /*
     * Busca todas as Contas
     */
    public List<Count> getAllCountPay() {
        return countRepository.findByType("PAY");
    }

    //-------------------------------------------------------------------------------------------------------------------
    //Metodos Genericos

    /*
     * Busca Conta por ID
     */
    public Count getCountPayByID(UUID id) {
        return countRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhuma Conta Encontrada"));
    }

    /*
     * Edita Contas
     */
    public Count editCount(UUID id, CountRequestDTO data) {
        Count count = countRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhuma Conta Encontrada"));

        count.setCategoria(data.categoria());
        count.setDescricao(data.descricao());
        count.setValor(data.valor());
        count.setVencimento(data.vencimento());
        count.setStatus(data.status());
        count.setType(data.type());

        return countRepository.save(count);
    }

    /*
     * Deleta Contas
     */
    public void deletCount(UUID id) {
        countRepository.deleteById(id);
    }

}
