package aiss.videominer.controller;

import aiss.videominer.model.Caption;
import aiss.videominer.repository.CaptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/captions")
public class CaptionController {

    @Autowired
    private CaptionRepository repository;

    // Listar todas las captions
    @GetMapping
    public List<Caption> findAll() {
        return repository.findAll();
    }

    // Buscar caption por ID
    @GetMapping("/{id}")
    public Caption findById(@PathVariable String id) {
        Optional<Caption> caption = repository.findById(id);
        if (!caption.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Caption no encontrada");
        }
        return caption.get();
    }
}