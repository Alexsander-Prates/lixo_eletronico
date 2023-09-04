package com.alexsanderprates.lixo_eletronico.controller;

import com.alexsanderprates.lixo_eletronico.dto.ClienteDTO;
import com.alexsanderprates.lixo_eletronico.entity.Cliente;
import com.alexsanderprates.lixo_eletronico.service.GerenciamentoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/entitys")
@Slf4j
public class GerenciamentoController {

    private final GerenciamentoService gerenciamentoService;

    public GerenciamentoController(GerenciamentoService gerenciamentoService) {
        this.gerenciamentoService = gerenciamentoService;
    }

    @ApiOperation(value = "Cadastrar Novo Cliente")
    @PostMapping("/cadastro_cliente")
    public ResponseEntity<String> cadastrarCliente(@RequestBody @Valid ClienteDTO clienteDTO){
        try{
            gerenciamentoService.salvarCliente(clienteDTO);
        }catch (Exception e){
            return new ResponseEntity<>("Erro cadastro Cliente", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Cliente cadastrado com sucesso", HttpStatus.OK);
    }

    /*@ApiOperation(value = "Cadastrar Nova Coletora")
    @PostMapping("/cadastro_coletora")
    public ResponseEntity<String> cadastrarColetora(@RequestBody ColetoraDTO coletoraDTO){
        try{
            gerenciamentoService.salvarColetora(coletoraDTO);
        }catch (Exception e){
            return new ResponseEntity<>("Erro cadastro Coletora", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Coletora cadastrado com sucesso", HttpStatus.OK);
    }*/


    @ApiOperation(value = "Alterar dados Cliente")
    @PutMapping("/alterar/{codigo}")
    public ResponseEntity<String> auterarCliente(@RequestBody ClienteDTO clienteDto, @PathVariable("codigo") Long codigo){
        try{
            gerenciamentoService.alterarCliente(codigo, clienteDto);
        }catch (Exception e){
            return new ResponseEntity<>("Erro ao alterar Cliente", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Cliente alterado com sucesso",HttpStatus.OK);

    }

    /*@ApiOperation(value = "Alterar dados Coletora")
    @PutMapping("/alterar/{codigo}")
    public ResponseEntity<String> auterarColetora(@RequestBody ColetoraDTO coletoraDTO, @PathVariable("codigo") Long codigo){
        try{
            gerenciamentoService.alterarColetora(coletoraDTO,codigo);
        }catch (Exception e){
            return new ResponseEntity<>("Erro ao alterar Coletora", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Coletora alterado com sucesso",HttpStatus.OK);

    }*/
    @ApiOperation(value = "Alterar Atributos Cliente")
    @PatchMapping("/alterarAtributo/{codigo}")
    public ResponseEntity<String> alterarPorAtributo(@PathVariable(name = "codigo") Long codigo,
                                                  @RequestBody Map<String,Object> campos){
        try {
            gerenciamentoService.alterarPorAtributoCliente(codigo,campos);
            return ResponseEntity.ok("Objeto alterado com sucesso");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @ApiOperation(value = "Excluir Cliente")
    @DeleteMapping("/excluir/{codigo}")
    public ResponseEntity<String> excluir(@PathVariable("codigo") Long codigo){
        try{
            gerenciamentoService.excluirCliente(codigo);
        }catch (Exception e){
            return new ResponseEntity<>("Erro ao excluir",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Excluido com sucesso",HttpStatus.OK);

    }

    @ApiOperation(value = "Consulta por Código")
    @GetMapping("/consultar/{codigo}")
    public ResponseEntity<Cliente> conultarCliente(@PathVariable("codigo") Long codigo){
        try{
            Optional<Cliente> cliente = gerenciamentoService.buscarClienteCodigo(codigo);
            if(Optional.ofNullable(cliente).isPresent())
                return new ResponseEntity<>(cliente.get(),HttpStatus.OK);
            return new ResponseEntity<>(new Cliente(),HttpStatus.NO_CONTENT);

        }catch (Exception e){
            return new ResponseEntity<>(new Cliente(),HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Consultar Todos Clientes")
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> consultatTodosClientes(){
        try{
            List<Cliente> clientes = gerenciamentoService.buscarTodosClientes();
                return new ResponseEntity<>(clientes,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Consultar por Nome de Cliente")
    @GetMapping("/cliente/nome")
    public ResponseEntity<List<Cliente>> consultatNomeCliente(@RequestParam("nome") String nome){
        try{
            List<Cliente> clientes = gerenciamentoService.buscarNomeCliente(nome);
                return new ResponseEntity<>(clientes,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }





}
