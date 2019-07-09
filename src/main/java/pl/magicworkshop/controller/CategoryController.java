package pl.magicworkshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.magicworkshop.exceptions.CategoryNotFoundException;
import pl.magicworkshop.exceptions.DataIntegrityViolationException;
import pl.magicworkshop.model.Category;
import pl.magicworkshop.repository.CategoryRepository;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CategoryController {

    private Scanner scanner;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(Scanner scanner, CategoryRepository categoryRepository){
        this.scanner = scanner;
        this.categoryRepository = categoryRepository;
    }

    public void addCategory() {
        Category category = new Category();
        try {
        System.out.println("Nazwa kategorii:");
        category.setName(scanner.nextLine());
        System.out.println("Opis kategorii:");
        category.setDescription(scanner.nextLine());
        System.out.println("Dodano kategorię");
        categoryRepository.save(category);
        }catch (DataIntegrityViolationException e){
            System.out.println("Operacja nie powiodłą się. Kategoria o podanej nazwie juz istnieje");
        }
        System.out.println(category);

    }



    public void removeCategory(){
        System.out.println("Podaj id kategorii do usunięcia: ");
        Optional<Category> category = findCategoryById(scanner.nextLong());
        scanner.nextLine();
        category.ifPresentOrElse(categoryRepository::delete, () -> {
            throw new CategoryNotFoundException("Kategoria o podanym ID nie istnieje");
        } );

    }

    private Optional findCategoryById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        return category;
    }
}
