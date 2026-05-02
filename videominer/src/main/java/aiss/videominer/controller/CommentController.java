package aiss.videominer.controller;

import aiss.videominer.model.Comment;
import aiss.videominer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/comments")
public class CommentController {

    @Autowired
    private CommentRepository repository;

    // Listar todos los comentarios
    @GetMapping
    public List<Comment> findAll() {
        return repository.findAll();
    }

    // Buscar comentario por ID
    @GetMapping("/{id}")
    public Comment findById(@PathVariable String id) {
        Optional<Comment> comment = repository.findById(id);
        if (!comment.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentario no encontrado");
        }
        return comment.get();
    }
}