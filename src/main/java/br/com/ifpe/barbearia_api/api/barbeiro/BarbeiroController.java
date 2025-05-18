package br.com.ifpe.barbearia_api.api.barbeiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.barbearia_api.modelo.barbeiro.Barbeiro;
import br.com.ifpe.barbearia_api.modelo.barbeiro.BarbeiroService;

@RestController
@RequestMapping("/api/barbeiro")
@CrossOrigin
public class BarbeiroController {

   @Autowired
   private BarbeiroService BarbeiroService;

   @PostMapping
   public ResponseEntity<Barbeiro> save(@RequestBody BarbeiroRequest request) {

      Barbeiro barbeiro = BarbeiroService.save(request.build());
       return new ResponseEntity<Barbeiro>(barbeiro, HttpStatus.CREATED);
   }
}
