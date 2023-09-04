package com.alexsanderprates.lixo_eletronico.service;

import com.alexsanderprates.lixo_eletronico.dto.ClienteDTO;
import com.alexsanderprates.lixo_eletronico.dto.ColetoraDTO;
import com.alexsanderprates.lixo_eletronico.entity.Cliente;
import com.alexsanderprates.lixo_eletronico.entity.Coletora;
import com.alexsanderprates.lixo_eletronico.repository.ClienteRepository;
import com.alexsanderprates.lixo_eletronico.repository.ColetoraRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GerenciamentoService {

    private final ClienteRepository clienteRepository;
    private final ColetoraRepository coletoraRepository;

    public GerenciamentoService(ClienteRepository clienteRepository, ColetoraRepository coletoraRepository) {
        this.clienteRepository = clienteRepository;
        this.coletoraRepository = coletoraRepository;
    }

    public Cliente salvarCliente(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEmail(clienteDTO.getEmail());
        //cliente.setProduto(clienteDTO.getProduto());
        cliente.setValor(clienteDTO.getValor());

        clienteRepository.save(cliente);

        return cliente;
    }

    /*public Coletora salvarColetora(ColetoraDTO coletoraDto){
        Coletora coletora = new Coletora();
        coletora.setEmpresa(coletoraDto.getEmpresa());
        coletora.setCnpj(coletoraDto.getCnpj());
        coletora.setEndereco(coletoraDto.getEndereco());
        coletora.setTelefone(coletoraDto.getTelefone());
        coletora.setEmail(coletoraDto.getEmail());
        coletora.setProduto(coletoraDto.getProduto());
        coletora.setPagamento(coletoraDto.getPagamento());


        coletoraRepository.save(coletora);

        return coletora;
    }*/

    public void alterarCliente(Long codigoCliente, ClienteDTO clienteDTO) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(codigoCliente);
        if(!cliente.isPresent())
            throw new Exception("Cliente não encontrado");
        cliente.get().setNome(clienteDTO.getNome());
        cliente.get().setCpf(clienteDTO.getCpf());
        cliente.get().setEndereco(clienteDTO.getEndereco());
        cliente.get().setTelefone(clienteDTO.getTelefone());
        cliente.get().setEmail(clienteDTO.getEmail());
        //cliente.get().setProduto(clienteDTO.getProduto());
        cliente.get().setValor(clienteDTO.getValor());
        clienteRepository.save(cliente.get());

    }

    /*public void alterarColetora(ColetoraDTO coletoraDTO, Long codigoColetora) throws Exception {
        //tanto inserção quanto alteração é a função salve()
        Optional<Coletora> coletora = coletoraRepository.findById(codigoColetora);
        if(!coletora.isPresent()|| coletora.isEmpty())
            throw new Exception("Cliente não encontrado");

        coletoraRepository.save(coletora.get());

    }*/


    public void alterarPorAtributoCliente(Long codigo, Map<String,Object> campos) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepository.findById(codigo);
        if(!clienteOptional.isPresent()){
            throw new Exception("Erro ao chamar");
        }
        Cliente cliente = clienteOptional.get();
        this.merge(campos,cliente);
        clienteRepository.save(cliente);

    }

    private void merge(Map<String,Object> campos,Cliente cliente){
        ObjectMapper objectMapper = new ObjectMapper();
        //ler e converte para objeto
        Cliente client = objectMapper.convertValue(campos, Cliente.class);
        campos.forEach((chave,valor) -> {
            Field field = ReflectionUtils.findField(Cliente.class, chave);
            field.setAccessible(true); //em tempo de compilação transformar de private p/ publico
            Object novoValor = ReflectionUtils.getField(field, client);
            System.out.println(chave+ " = " + valor + " = " + novoValor);
            ReflectionUtils.setField(field,cliente,novoValor);
        });
    }

//    public void alterarTelefoneColetora(String telefone, Long codigo){
//        Optional<Coletora> coletora = coletoraRepository.findById(codigo);
//        if(!Optional.ofNullable(coletora).isPresent()){
//            coletora.get().setTelefone(telefone);
//            coletoraRepository.save(coletora.get());
//        }
//
//    }

    public void excluirCliente(Long codigo){
        clienteRepository.deleteById(codigo);
    }

    public Optional<Cliente> buscarClienteCodigo(Long codigo){

        return clienteRepository.findById(codigo);
    }

    public List<Cliente> buscarTodosClientes(){
        return clienteRepository.findAll();
    }

    public List<Cliente> buscarNomeCliente(String nome){
        return clienteRepository.buscarNomeCliente(nome);
    }

}
