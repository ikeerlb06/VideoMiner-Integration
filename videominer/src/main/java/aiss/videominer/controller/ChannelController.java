package aiss.videominer.controller;

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

    // Operación POST para añadir un nuevo canal
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Channel create(@RequestBody Channel channel) {
        return repository.save(channel);
    }

}
}