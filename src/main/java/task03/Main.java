package task03;

public class Main {

    public static void main(String[] args) {

        // Создаём объект продуктового сервиса
        ProductService service = new ProductService();

        // Добавляем продукты для хранения в сервис
        service.addProduct(new Product("Банан", 120));
        service.addProduct(new Product("Яблоко", 70));
        service.addProduct(new Product("Персик", 230));

        try {
            // Предположим, что мы хотим узнать цену на яблоки
            Product product = service.findByTitle("Яблоко");
            System.out.println("Цена яблока - " + product.getPrice());

            // Теперь попытаемся узнать цену на лимоны
            product = service.findByTitle("Лимон");
            System.out.println("Цена лимона - " + product.getPrice());
        } catch (ProductNotFoundException e) {
            System.out.println("Ошибка - " + e.getMessage());
        }
    }
}
