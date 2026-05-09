package aiss.videominer.controller;

import org.springframework.http.HttpStatus;
import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videominer/channels") // Esta es la ruta base para los canales
public class ChannelController {

    @Autowired
    private ChannelRepository repository;

    // Operación GET para listar todos los canales
    @GetMapping
    public List<Channel> findAll() {
        return repository.findAll();
    }

    // Operación GET para buscar un canal específico por su ID
    @GetMapping("/{id}")
    public Channel findById(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }

    // Operación POST para añadir un nuevo canal
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Channel create(@RequestBody Channel channel) {
        return repository.save(channel);
    }

}
