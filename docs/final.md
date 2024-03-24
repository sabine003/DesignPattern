## SOLID 
1- Single Responsibility Principle (SRP)

Classes like CsvFileHandler, JsonFileHandler, and each Command implementation: Each class is focused on a single responsibility, such as handling data in a specific format or executing a specific command, aligning well with SRP.

2- Open-Closed Principle (OCP)

FileHandlerFactory: It enables extending the application to support new file formats without altering existing code, embodying the principle of being open for extension but closed for modification.

3- Liskov Substitution Principle (LSP)

FileHandler Interface Implementations: The design allows CsvFileHandler and JsonFileHandler to be interchangeably used without affecting the functionality, adhering to LSP by ensuring subclass objects can replace superclass objects.

4- Interface Segregation Principle (ISP)

The application's use of focused interfaces, particularly through the Command interface for different actions, leans towards ISP by ensuring that implementing classes only need to be concerned with relevant methods.

5- Dependency Inversion Principle (DIP)

Use of FileHandler Interface: TodoFileStorage depends on the abstract FileHandler interface rather than concrete implementations, demonstrating DIP by relying on abstractions rather than specifics.
