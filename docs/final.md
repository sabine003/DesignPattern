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

### Project Extension Guide
This document serves as a quick guide for newcomers to extend the functionality of the TODO application.

1- Adding a New Command
Implement the Command interface: Create a new class in the com.fges.todoapp package that implements the Command interface.

Define functionality: Implement the execute method with the logic specific to your new command.
Register your command: Update the CommandLineExecutor class to recognize your new command. Add a case in the switch statement within the execute method.

2- Adding a New File-based Datasource
Implement the FileHandler interface: Create a class for your new file format, implementing the FileHandler interface to handle file operations.

Factory modification: Update the FileHandlerFactory class to return an instance of your new handler when the appropriate file extension is detected.

3- Adding a Non-file-based Datasource
Create a new datasource handler: This could be a class that implements a common interface for data handling, similar to FileHandler, but for your specific datasource (e.g., a database).

Integrate with TodoFileStorage: Adjust TodoFileStorage to recognize and utilize the new datasource handler based on some configuration or parameter.

4- Adding a New Attribute to a Todo
Update the Todo class: Add the new attribute with appropriate getters and setters.
Modify file handlers: Ensure CsvFileHandler and JsonFileHandler are updated to read and write the new attribute.

5- Adding a New Interface to the Project
Define the interface: Create a new interface in the com.fges.todoapp package that declares the methods to be implemented.
Implement the interface: Create classes that implement your new interface, providing specific functionalities.


![image](https://github.com/sabine003/DesignPattern/assets/88795763/a8de980c-4847-49fb-a883-6ca5bf1c9ea6)
