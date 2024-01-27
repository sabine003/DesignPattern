# L3 design pattern report

- **Firstname**: Sabine
- **Lastname**: Mhanna


> Add your thoughts on every TP bellow, everything is interresting but no need to right a book.
> 
> Keep it short simple and efficient:
> 
> - **What you did and why**

	SOLID PRINCIPLES
  1- Single Responsibility Principle 
  
In the original code, the class App has many responsibilities like handling two types of files (JSON and CSV) and handling the commands like insert and list, thus it has many reasons to change. 
First step is to split App according to its responsibilities. 
	
  2- The Open-Closed Principle
  
The original code violates the open-closed principle because it supports two types of files like JSON and CSV. Adding a new type of files is hard, we need to hunt for every place that contains conditional logic to distinguish between the types of files and add code to it.

> - What helped you and why
> - **What did you find difficult**

I found it diffuclt to know from where exactly should i start and how to link the classes together.
> - What did not help you
> - **What did you need to change**

I added a FileHandler interface that abstracts the file handling operations, allowing us to create different implementations for different file formats (JSON and CSV). JsonFileHandler and CsvFileHandler are implementations of the FileHandler interface, each handling specific file format operations.

I also added a Command interface that abstracts commands operations on the files, allowing us to create different implementations for different commands like insert and list. ListCommand and InsertCommand are implementations of the Command interface, each handling specific operations on the files.

These changes helped us not violate the SRP and open-closed principle  
> - Anything relevant
> 
> **Add a link to schemas describing your architecture (UML or not but add a legend)**

![FileHandlerUML](https://github.com/sabine003/DesignPattern/assets/88795763/281c381b-79b6-4548-be4a-88383c45cd74)
![CommandUML](https://github.com/sabine003/DesignPattern/assets/88795763/d113819b-6171-474a-9972-b1d416cedd8d)


> Remember: it is ok to make mistakes, you will have time to spot them later.
> 
> Fill free to contact me if needed.

---
...
