package br.com.dh.ecommerce.controllers;

import br.com.dh.ecommerce.dtos.CategoryDto;
import br.com.dh.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAllCategories() {
        List<CategoryDto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Integer id) {
        CategoryDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CategoryDto> insertCategory(@RequestBody CategoryDto dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Integer id, @RequestBody CategoryDto dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }


}