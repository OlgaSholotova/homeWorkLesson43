package task03;

import java.util.ArrayList;
import java.util.List;

/*
Это класс, который является описанием сервиса продуктов.
Задача сервиса - хранить список продуктов и осуществлять различные
операции с продуктами, которые хранятся в этом списке.
Например - добавить новый продукт в список, найти продукт в списке по названию,
изменить цену определённого продукта, удалить продукт и т.д.
 */
public class ProductService {

    // Это лист, который используется для хранения продуктов внутри сервиса
    private List<Product> products = new ArrayList<>();

    // Метод, который служит для добавления продуктов в сервис
    public void addProduct(Product product) {
        if (product == null) { //сюда, в процессе создания тестов для проверки корректной работы сервиса я добавила
            // проверку на null
            throw new IllegalArgumentException("Продукт не может быть null");
        }
        boolean exists = products.stream()
                .anyMatch(x -> x.getTitle().equals(product.getTitle()));
        if (exists) {
            throw new IllegalArgumentException("Продукт с названием " + product.getTitle() + " уже существует. Дубликаты добавлять запрещено!");
        }
        products.add(product);
    }

    // Метод, который находит и возвращает продукт по его названию
    // Правило: если метод где-то в своём коде выбрасывает проверяемое исключение,
    // то такой метод обязан это задекларировать в своей сигнатуре
    public Product findByTitle(String title) throws ProductNotFoundException {
        Product product = products
                .stream()
                .filter(x -> x.getTitle().equals(title))
                .findFirst()
                .orElse(null);

        // Если в предыдущем стриме продукт не найдётся, то в переменную запишется null.
        // И тогда эта строка вернёт null. Такой подход неинформативен.
        // Тот, кто вызывает наш метод и получает в ответ null, может и не понять причин,
        // а почему именно пришёл null, потому что причины могут быть разные.
//        return product;

        // Этот подход гораздо лучше, так как создаёт объект ошибки
        // с информативным описанием причины, что именно пошло не так.
        // Но этот подход всё ещё не максимально информативен.
        // Лучше, когда уже само название эксепшена чётко говорит о проблеме.
//        if (product == null) {
//            throw new IllegalArgumentException("Продукт " + title + " не найден");
//        }
//        return product;

        // Теперь всё ещё более информативно,
        // только по названию эксепшена уже можно догадаться о причинах.
        if (product == null) {
            throw new ProductNotFoundException("Продукт " + title + " не найден");
        }
        return product;
    }
}
