package imt.example.springshopbe.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import imt.example.springshopbe.domain.Category;
import imt.example.springshopbe.dto.CategoryDto;
import imt.example.springshopbe.service.CategoryService;
import imt.example.springshopbe.service.MapValidationErrorService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    CategoryDto categoryDto ;
    @Autowired
    CategoryService categoryService ;

    @Autowired
    MapValidationErrorService mapValidationErrorService ;
@PostMapping
    public ResponseEntity<?> creatEntity(@Valid
            @RequestBody CategoryDto categoryDto,
                                         BindingResult result){
    ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(result) ;
    if(responseEntity != null){
        return responseEntity ;
    }
    Category entity = new Category( );
    BeanUtils.copyProperties(categoryDto,entity);
    entity = categoryService.save(entity);
    categoryDto.setId(entity.getId());
    return  new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
}
@PatchMapping("/{id}")
    public ResponseEntity<?> updateEntity(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto){
        Category entity = new Category( );
        BeanUtils.copyProperties(categoryDto,entity);
        entity = categoryService.update(id,entity);
        categoryDto.setId(entity.getId());
        return  new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }
@GetMapping
    public ResponseEntity<?> getAll(){
    return new ResponseEntity<>(categoryService.getAll() ,HttpStatus.OK) ;
}

    @GetMapping("/getPage")
    public ResponseEntity<?> getAllPage(@PageableDefault(size = 5,sort = "name", direction = Sort.Direction.ASC) Pageable pageable){
        return new ResponseEntity<>(categoryService.findAll(pageable) ,HttpStatus.OK) ;
    }

    @GetMapping("/{id}/getid")
    public ResponseEntity<?> getByid(@PathVariable("id") Long id){
    return new ResponseEntity<>(categoryService.getById(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        categoryService.deleteById(id);
        return new ResponseEntity<>("Da xoa thanh cong",HttpStatus.OK) ;
    }
}
