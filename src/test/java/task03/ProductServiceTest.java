package task03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    //объявляем поле - .....
    private ProductService service;

    // выносим в заготовку для всех текстов создание объекта сервиса, с экзмплярами продуктов
    @BeforeEach
    public void setUp() {
        service = new ProductService();
        service.addProduct(new Product("Банан", 120));
        service.addProduct(new Product("Яблоко", 70));
    }

    // 1.Добавление нового продукта.
    @Test
    public void checkCreateNewProduct() throws ProductNotFoundException {
        //создаем новый продукт для этого тестового метода
        Product expectedProduct = new Product("Персик", 120);
        service.addProduct(expectedProduct); // вызываем метод, который тестируем. т.к. метод возвращает void мы 
        // результаты его работы не можем  сохранить в переменную (созданный продукт) , поэтому или надо в самом  проверяемом
        // методе переписывать  чтобы он был не void a Product и возвращал обьект продукта ( но я не рискну менять
        // исходный код и поэтому ниже воспользуюсь поиском  продукта чтобы проверить, что он точно был добавлен.
        Product actualProduct = service.findByTitle("Персик");
        assertEquals(expectedProduct, actualProduct, "Продукт не был добавлен корректно"); // сравниваем чтобы
        // убедиться что добавление прошло корректно
    }

    //2. Добавление дубликата продукта:
    // для написания этого теста я дополнила осн. код в классе ProductService метод по созданию нового продукта - проверками
    // на создание дубликата и получение null вместо продукта. при этих нарушениях метод должен выбрасывать ошибку IllegalArgumentException
    @Test
    public void checkCreateDuplicateOfProduct() {
        Product duplicateProduct = new Product("Банан", 120);
        assertThrows(
                IllegalArgumentException.class,
                () -> service.addProduct(duplicateProduct),
                "метод не выбросил ошибку  IllegalArgumentException при попытке создания дубликата продукта ");
    }

    //3. получение null вместо продукта:
    @Test
    public void checkIfProductIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.addProduct(null),
                "метод не выбросил ошибку  IllegalArgumentException при получении null вместо продукта ");
    }

    //4.Поиск продукта
    @Test
    public void checkSearchProduct() throws ProductNotFoundException {
        Product expectedProduct = new Product("Банан", 120);
        Product actualProduct = service.findByTitle("Банан");
        assertEquals(expectedProduct, actualProduct, "Метод вернул не верный продукт");
    }
}
