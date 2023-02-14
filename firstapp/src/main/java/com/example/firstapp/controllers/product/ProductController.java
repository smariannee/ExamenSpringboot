package com.example.firstapp.controllers.product;

import com.example.firstapp.controllers.product.dto.ProductDto;
import com.example.firstapp.models.product.Product;
import com.example.firstapp.services.product.ProductService;
import com.example.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-firstapp/product")
@CrossOrigin(origins = {"*"})
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Product>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Product>> getOne(@PathVariable long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Product>> insert(
            @Valid @RequestBody ProductDto product, BindingResult result
    ){
        return new ResponseEntity<>(
                this.service.insert(product.castToProduct()),
                HttpStatus.OK
        );
    }
}
