package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.count.Count;
import com.pdvsystem.api.domain.count.CountRequestDTO;
import com.pdvsystem.api.repositories.CountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CountService {

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
        count.setType("Receber");

        return countRepository.save(count);
    }

    /*
     * Busca todas as Contas a Receber
     */
    public List<Count> getAllCountRecieve() {
        List<Count> contas = countRepository.findAll();
        contas.forEach(count -> {
                    if (!count.getType().equals("Receber")) {
                        contas.remove(count);
                    }
                }
        );
        return contas;
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
        count.setType("Pagar");

        return countRepository.save(count);
    }

    /*
     * Busca todas as Contas
     */
    public List<Count> getAllCountPay() {
        List<Count> contas = countRepository.findAll();
        contas.forEach(count -> {
                    if (!count.getType().equals("Pagar")) {
                        contas.remove(count);
                    }
                }
        );
        return contas;
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
