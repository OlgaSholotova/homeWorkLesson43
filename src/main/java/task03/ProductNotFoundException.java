package task03;

// Если создаём непроверяемое исключение - наследуемся, например, от RuntimeException
// Если создаём проверяемое исключение - наследуемся, например, от Exception
public class ProductNotFoundException extends Exception {

    // Этот конструктор позволяет нам добавлять информативное сообщение
    // об ошибке в момент создания объекта ошибки
    public ProductNotFoundException(String message) {
        super(message);
    }
}
