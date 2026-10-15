package fr.portfolio.stock;
import java.util.List; import jakarta.validation.Valid; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*; import org.springframework.web.server.ResponseStatusException;
/** Contrôleur REST : validation HTTP et délégation des règles métier au service. */
@RestController @RequestMapping("/api/products") public class ProductController {
 private final ProductRepository repo; public ProductController(ProductRepository repo){this.repo=repo;}
 @GetMapping public List<Product> all(){return repo.findAll();}
 @GetMapping("/{id}") public Product get(@PathVariable Long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Product create(@Valid @RequestBody Product p){if(repo.existsBySku(p.getSku()))throw new ResponseStatusException(HttpStatus.CONFLICT,"SKU déjà utilisé");return repo.save(p);}
 @PutMapping("/{id}") public Product update(@PathVariable Long id,@Valid @RequestBody Product p){Product old=get(id);if(!old.getSku().equals(p.getSku())&&repo.existsBySku(p.getSku()))throw new ResponseStatusException(HttpStatus.CONFLICT,"SKU déjà utilisé");old.setSku(p.getSku());old.setName(p.getName());old.setQuantity(p.getQuantity());return repo.save(old);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){repo.delete(get(id));}
 @PatchMapping("/{id}/stock") public Product adjust(@PathVariable Long id,@RequestParam int delta){Product p=get(id);if(p.getQuantity()+delta<0)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Stock insuffisant");p.setQuantity(p.getQuantity()+delta);return repo.save(p);}
}
