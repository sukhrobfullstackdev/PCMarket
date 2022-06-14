package uz.pcmarket.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pcmarket.pcmarket.entity.Category;
import uz.pcmarket.pcmarket.payload.CategoryDto;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.service.CategoryService;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public ResponseEntity<Page<Category>> getCategories(@RequestParam int page, @RequestParam int size) {
        return categoryService.getCategoriesService(page, size);
    }

    @PreAuthorize(value = "hasAuthority('READ_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer id) {
        return categoryService.getCategoryService(id);
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ResponseEntity<Message> addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategoryService(categoryDto);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        return categoryService.editCategoryService(id, categoryDto);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteCategory(@PathVariable Integer id) {
        return categoryService.deleteCategoryService(id);
    }
}
