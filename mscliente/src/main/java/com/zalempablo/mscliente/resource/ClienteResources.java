package com.zalempablo.mscliente.resource;

import com.zalempablo.mscliente.representation.ClienteSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteResources {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String status(){
        log.info("Obtendo o status do microsservico do cliente");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request){
        var clientes = request.toModel();
        clienteService.save(clientes);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(clientes.getCpf())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosClientes(@RequestParam String cpf){
        var clientes = clienteService.getByCpf(cpf);
        if(clientes.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientes);
    }
}
