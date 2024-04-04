# Class: Client

## Description
The `Main` class serves as the entry point for the client-side of the library application. It establishes a connection with the server, handles user input, and performs corresponding actions based on selected options.

## Methods

### `public static void main(String[] args)`
The main method is the entry point of the program. It initializes the connection to the server, creates input and output streams, and starts the user interaction loop.

#### Parameters
- `args`: Command-line arguments (not used in this application).

#### Local Variables
- `socket`: Socket object to establish a connection with the server.
- `inputStreamReader`: InputStreamReader object for reading data from the socket.
- `outputStreamWriter`: OutputStreamWriter object for writing data to the socket.
- `bufferedReader`: BufferedReader object for efficient reading of characters from the stream.
- `bufferedWriter`: BufferedWriter object for efficient writing of characters to the stream.
- `scanner`: Scanner object for reading data from the console.
- `tempVar1`, `tempVar2`, `tempVar3`: Temporary variables to store user input and messages from the server.
- `msgFromServer`: String to store messages from the server.
- `command`: String for sending commands to the server.
- `option`: Integer variable for user option selection.

#### Code Blocks
1. Initialization of the socket and input/output streams.
2. User interaction loop:
   - Prompting the user to choose an option (1 - Login, 2 - Registration).
   - Handling the selected option:
     - Option 1 (Login): Requesting account data, sending it to the server, and processing the response.
     - Option 2 (Registration): Requesting data to create a new account, sending it to the server, and processing the response.
     - Options for library management: adding a book, viewing books, administrative functions.
     - Option 0: Exiting the loop.
     - Handling incorrect choices.

#### Exception Handling
- Handling IOException to prevent possible errors when working with the socket and input/output streams.

## Note
- The code contains a commented-out section that can be uncommented in the further development of the program.
- To improve code readability, consider extracting the logic for handling options into separate methods.


# Class: Server

## Description
The `Main` class represents the server-side of the library application. It listens for incoming client connections, establishes communication channels, and delegates requests to appropriate handlers.

## Methods

### `public static void main(String[] args) throws IOException`
The main method is the entry point of the server application. It sets up a ServerSocket, accepts incoming client connections, and spawns threads to handle client requests.

#### Parameters
- `args`: Command-line arguments (not used in this application).

#### Local Variables
- `socket`: Socket object representing the client-server connection.
- `inputStreamReader`: InputStreamReader for reading data from the client.
- `outputStreamWriter`: OutputStreamWriter for writing data to the client.
- `bufferedReader`: BufferedReader for efficient reading of characters from the stream.
- `bufferedWriter`: BufferedWriter for efficient writing of characters to the stream.
- `serverSocket`: ServerSocket for accepting client connections.
- `tempVar1`, `tempVar2`, `tempVar3`, `privilege`, `msgOut`, `tempVar4`: Temporary variables for handling client requests.
- `autorization`: Instance of the Autorization class for user authorization.
- `libraryManager`: Instance of the LibraryManager class for managing the library.

#### Code Blocks
1. Set up the ServerSocket to listen for incoming connections.
2. Accept client connections and create communication channels.
3. Handle client requests based on received commands (Login, Registration, statusCheck, newBook, newAccount, showBooks, delBook).
4. Delegate specific actions to the Autorization and LibraryManager classes.
5. Close resources after client interaction.

#### Exception Handling
- Handling IOException to prevent possible errors when working with sockets and streams.

# Class: LibraryManager

## Description
The `LibraryManager` class handles operations related to the library, such as adding new books, displaying books, and deleting books.

## Methods

### `public boolean newBook(String author, String name, String genre, String owner)`
Adds a new book to the library.

#### Parameters
- `author`: Author of the book.
- `name`: Name of the book.
- `genre`: Genre of the book.
- `owner`: Owner of the book.

#### Local Variables
- `connection`: Connection object for connecting to the database.
- `preparedStatement`: PreparedStatement for executing SQL queries.
- `rowsAffected`: Integer variable to store the number of affected rows.

#### Code Blocks
1. Establish a connection to the database.
2. Prepare an SQL query to insert a new book into the database.
3. Set parameters for the query (author, name, genre, owner).
4. Execute the query and check the number of affected rows.
5. Print a success message if the book is added successfully.

#### Exception Handling
- Handling SQLException to catch any database-related errors.

### `public List<String> showBooks()`
Retrieves and returns a list of all books in the library.

#### Return
- `books`: List of strings representing book names.

#### Local Variables
- `connection`: Connection object for connecting to the database.
- `preparedStatement`: PreparedStatement for executing SQL queries.
- `resultSet`: ResultSet for storing the results of the SQL query.
- `books`: List to store book names.

#### Code Blocks
1. Establish a connection to the database.
2. Prepare an SQL query to select all books from the database.
3. Execute the query and process the results.
4. Add each book name to the list.
5. Return the list of books.

#### Exception Handling
- Handling SQLException to catch any database-related errors.

### `public boolean delBook(String name, String owner)`
Deletes a book from the library.

#### Parameters
- `name`: Name of the book to be deleted.
- `owner`: Owner of the book.

#### Local Variables
- `connection`: Connection object for connecting to the database.
- `preparedStatement`: PreparedStatement for executing SQL queries.
- `rowsAffected`: Integer variable to store the number of affected rows.

#### Code Blocks
1. Establish a connection to the database.
2. Prepare an SQL query to delete a book based on its name and owner.
3. Set parameters for the query (name, owner).
4. Execute the query and check the number of affected rows.
5. Print a success message if the book is deleted successfully.

#### Exception Handling
- Handling SQLException to catch any database-related errors.

# Class: Autorization

## Description
The `Autorization` class handles user authorization and registration.

## Methods

### `public boolean autorizate(String userLogin, String userPassword)`
Authorizes a user based on the provided login and password.

#### Parameters
- `userLogin`: User's login.
- `userPassword`: User's password.

#### Local Variables
- `connection`: Connection object for connecting to the database.
- `preparedStatement`: PreparedStatement for executing SQL queries.
- `resultSet`: ResultSet for storing the results of the SQL query.

#### Code Blocks
1. Establish a connection to the database.
2. Prepare an SQL query to select a user based on login and password.
3. Set parameters for the query (userLogin, userPassword).
4. Execute the query and check if a matching user is found.

#### Exception Handling
- Handling SQLException to catch any database-related errors.

### `public boolean newRegistration(String newLogin, String newPassword)`
Registers a new user with the provided login and password.

#### Parameters
- `newLogin`: New user's login.
- `newPassword`: New user's password.

#### Local Variables
- `connection`: Connection object for connecting to the database.
- `preparedStatement`: PreparedStatement for executing SQL queries.
- `rowsAffected`: Integer variable to store the number of affected rows.

#### Code Blocks
1. Establish a connection to the database.
2. Prepare an SQL query to insert a new user into the database.
3. Set parameters for the query (newLogin, newPassword).
4. Execute the query and check the number of affected rows.
5. Print a success message if the registration is successful.

#### Exception Handling
- Handling SQLException to catch any database-related errors.

### `public boolean checkStatus(String userName)`
Checks the status of a user (admin or regular user).

#### Parameters
- `userName`: User's login.

#### Local Variables
- `connection`: Connection object for connecting to the database.
- `preparedStatement`: PreparedStatement for executing SQL queries.
- `resultSet`: ResultSet for storing the results of the SQL query.
- `privilege`: String variable to store the user's privilege.

#### Code Blocks
1. Establish a connection to the database.
2. Prepare an SQL query to select the privilege of the user based on the login.
3. Set parameters for the query (userName).
4. Execute the query and check if a matching user is found.
5. Retrieve the privilege of the user and check if it is equal to "admin."

#### Exception Handling
- Handling SQLException to catch any database-related errors.
