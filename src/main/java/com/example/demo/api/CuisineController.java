package com.example.demo.api;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.Cuisine;
import com.example.demo.domain.repository.CuisineRepository;
import com.example.demo.domain.service.CuisineService;

@RequestMapping("cuisine")
@RestController
public class CuisineController {

  // esse lance de termos repository e service aqui na controller não foi por
  // acaso
  // segundo o professor da alga, ele não vê problema quando a controller acessa o
  // repositório diretamente somente para pegar listas, mas, quando ele acessa pra
  // alterar o conteúdo, ai ele acha interessante fazer isso na camada de serviço,
  // que deve conter as famigeradas regras de negócio
  @Autowired
  private CuisineRepository cuisineRepository;
  @Autowired
  private CuisineService cuisineService;

  // segundo o professor da algaworks, o correto é o código 200 quando não houver
  // nenhuma cuisine cadastrada, pois o 404 só deve
  // ser usado quando algo não foi encontrado e há erro por parte da requisição do
  // cliente, Ainda segundo ele, 204 não seria viável, pois 204 significa nenhum
  // conteúdo, e quando uma lista vazia é retornada, [], há o retorno de algum
  // conteúdo, ou seja, uma lista vazia.
  @GetMapping
  public List<Cuisine> getAll() {
    return cuisineRepository.findAll();
  }

  @GetMapping("{id}")
  public Cuisine getById(@PathVariable Long id) {
    return cuisineService.searchOrFail(id);
  }

  @GetMapping("/getByNome/{name}")
  public ResponseEntity<List<Cuisine>> getByNome(@PathVariable String name) {
    List<Cuisine> cuisines = cuisineRepository.findByNameContaining(name);

    return ResponseEntity.ok(cuisines);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Cuisine create(@RequestBody Cuisine cuisine) {
    return cuisineService.save(cuisine);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PutMapping("{id}")
  public Cuisine update(@PathVariable Long id, @RequestBody Cuisine cuisine) {
    Cuisine actualCuisine = cuisineService.searchOrFail(id);

    BeanUtils.copyProperties(cuisine, actualCuisine, "id");

    return cuisineService.save(actualCuisine);  
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    cuisineService.remove(id);
  }
}
