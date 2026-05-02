package aiss.videominer.controller;

import aiss.videominer.model.Caption;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/videos")
public class VideoController {

    @Autowired
    private VideoRepository repository;

    // Listar todos los vídeos
    @GetMapping
    public List<Video> findAll() {
        return repository.findAll();
    }

    // Buscar vídeo por ID
    @GetMapping("/{id}")
    public Video findById(@PathVariable String id) {
        Optional<Video> video = repository.findById(id);
        if (!video.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vídeo no encontrado");
        }
        return video.get();
    }

    // Devolver las captions de un vídeo dado su ID
    @GetMapping("/{id}/captions")
    public List<Caption> findCaptionsByVideoId(@PathVariable String id) {
        Optional<Video> video = repository.findById(id);
        if (!video.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vídeo no encontrado");
        }
        return video.get().getCaptions();
    }

    // Devolver los comentarios de un vídeo dado su ID
    @GetMapping("/{id}/comments")
    public List<Comment> findCommentsByVideoId(@PathVariable String id) {
        Optional<Video> video = repository.findById(id);
        if (!video.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vídeo no encontrado");
        }
        return video.get().getComments();
    }
}
