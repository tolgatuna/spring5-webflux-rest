package guru.springframework.spring5webfluxrest.controllers;


import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by jt on 12/30/17.
 */
@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping()
    Flux<Vendor> list(){
        return vendorRepository.findAll();
    }

    @GetMapping("/{id}")
    Mono<Vendor> getById(@PathVariable String id){
        return vendorRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    Mono<Void> create(@RequestBody Publisher<Vendor> vendorPublisher) {
        return vendorRepository.saveAll(vendorPublisher).then();
    }

    @PutMapping("/{id}")
    Mono<Vendor> crate(@PathVariable String id, @RequestBody Vendor vendorMono) {
        return vendorRepository.save(vendorMono);
    }
}
