package uz.pcmarket.pcmarket.service;

import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pcmarket.pcmarket.entity.Category;
import uz.pcmarket.pcmarket.payload.CategoryDto;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.repository.CategoryRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Page<Category>> getCategoriesService(int page, int size) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findAll(PageRequest.of(page, size)));
    }

    public ResponseEntity<Category> getCategoryService(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.map(category -> ResponseEntity.status(HttpStatus.OK).body(category)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<Message> addCategoryService(CategoryDto categoryDto) {
        if (!Objects.equals(categoryDto.getName(), "")) {
            if (categoryRepository.existsByName(categoryDto.getName())) {
                if (!(categoryDto.getCategoryParentId() == null) || !(categoryDto.getCategoryParentId() == 0)) {
                    Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryParentId());
                    if (optionalCategory.isPresent()) {
                        categoryRepository.save(new Category(categoryDto.getName(), categoryDto.isActive(), optionalCategory.get()));
                        return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The category was successfully added!"));
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The parent category was not found!"));
                    }
                } else {
                    categoryRepository.save(new Category(categoryDto.getName(), categoryDto.isActive()));
                    return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The category was successfully added!"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "This category is already exists!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "Please fill the name of category!"));
        }
    }

    public ResponseEntity<Message> editCategoryService(Integer id, CategoryDto categoryDto) {
        if (!Objects.equals(categoryDto.getName(), "")) {
            Optional<Category> optionalCategory = categoryRepository.findById(id);
            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                category.setName(categoryDto.getName());
                category.setActive(categoryDto.isActive());
                if (categoryDto.getCategoryParentId() != null && categoryDto.getCategoryParentId() != 0) {
                    Optional<Category> parentCategory = categoryRepository.findById(categoryDto.getCategoryParentId());
                    if (parentCategory.isPresent()) {
                        category.setCategory(parentCategory.get());
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The parent category has not been found!"));
                    }
                }
                categoryRepository.save(category);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The category has been successfully edited!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The category is not found!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "Please fill the name of category!"));
        }
    }

    public ResponseEntity<Message> deleteCategoryService(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.delete(optionalCategory.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The category was deleted successfully!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The category that you want to delete is not found!"));
        }
    }
}
