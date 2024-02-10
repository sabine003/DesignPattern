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

**TP1**
I added a FileHandler interface that abstracts the file handling operations, allowing us to create different implementations for different file formats (JSON and CSV). JsonFileHandler and CsvFileHandler are implementations of the FileHandler interface, each handling specific file format operations.

I also added a Command interface that abstracts commands operations on the files, allowing us to create different implementations for different commands like insert and list. ListCommand and InsertCommand are implementations of the Command interface, each handling specific operations on the files.

These changes helped us not violate the SRP and open-closed principle

**TP2**
First of all I changed my initial code that i worked on last week so it can be more efficient and I rearranged the classes into different files following what was said in class. 

Added the --done Option: I added the --done option to the Options object using cliOptions.addOption("d", "done", false, "Mark todo as done");. This option allows users to specify whether a todo should be marked as done when inserting it.

Checking for the --done Flag: Inside the execute method, I checked if the --done flag is present in the command-line arguments using cmd.hasOption("d"). If the --done flag is present, it means that the user wants to mark the todo as done; otherwise, it remains as not done.

Passing markAsDone to insertTodo: I modified the insertTodo method in the CommandLineExecutor class to accept an additional parameter boolean markAsDone. This parameter represents whether the todo should be marked as done. When calling the insertTodo method, I passed the value of markAsDone based on whether the --done flag was present.

**TP3**
First of all I changed FileHandlerTest to FileHandlerFactory

Since in my previous code i had a bad service abstraction that makes it hard to add other commands in the futur, so i refactured 'CommandLineExecutor' for better command handling. 
I used a Command interface and command classes like InsertCommand and ListCommand that encapsulates each command's execution logic. This design makes it easy to add new commands in the future without modifying existing code, adhering to the Open/Closed Principle.

I fixed the --done, as it was not implemented right, now all the four arguments works fine

✔️ ./exec.sh insert "I am done" --done -s file.json

✔️ ./exec.sh insert "I am not done" -s file.json 

✔️ ./exec.sh list -s file.json

✔️ ./exec.sh list -s file.json --done

> - Anything relevant
> 
> **Add a link to schemas describing your architecture (UML or not but add a legend)**

**TP1**

![FileHandlerUML](https://github.com/sabine003/DesignPattern/assets/88795763/281c381b-79b6-4548-be4a-88383c45cd74)
![CommandUML](https://github.com/sabine003/DesignPattern/assets/88795763/d113819b-6171-474a-9972-b1d416cedd8d)

**TP2**

![tp2UML](https://github.com/sabine003/DesignPattern/assets/88795763/95b2c6c1-fec2-4354-b7ee-7caa7b8f03da)

> Remember: it is ok to make mistakes, you will have time to spot them later.
> 
> Fill free to contact me if needed.

---
...
