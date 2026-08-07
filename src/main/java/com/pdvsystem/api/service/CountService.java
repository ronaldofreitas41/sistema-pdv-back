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
        count.setUserId(data.userId());
        try {
            // may be null in some calls
            count.setCompanyId(data.companyId());
        } catch (Exception ignored) {}

        return countRepository.save(count);
    }

    /*
     * Busca todas as Contas a Receber
     */
    public List<Count> getAllCountRecieve() {
        return countRepository.findByType("RECIEVE");
    }

    public List<Count> getAllCountRecieveByUser(String userId) {
        return countRepository.findByTypeAndUserId("RECIEVE", userId);
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
        count.setUserId(data.userId());
        try {
            count.setCompanyId(data.companyId());
        } catch (Exception ignored) {}

        return countRepository.save(count);
    }

    /*
     * Busca todas as Contas
     */
    public List<Count> getAllCountPay() {
        return countRepository.findByType("PAY");
    }

    public List<Count> getAllCountPayByUser(String userId) {
        return countRepository.findByTypeAndUserId("PAY", userId);
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
        count.setUserId(data.userId());
        try { count.setCompanyId(data.companyId()); } catch (Exception ignored) {}

        return countRepository.save(count);
    }

    /*
     * Deleta Contas
     */
    public void deletCount(UUID id) {
        countRepository.deleteById(id);
    }

}
