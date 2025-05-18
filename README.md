# Student Server

This is a server application built using IntelliJ IDEA and the Ktor framework. It provides REST API endpoints for fetching, updating, deleting, and adding data to a Mongo database. The server utilizes various modern technologies including DataStore, Koin for dependency injection, Ktor client for networking with REST API, Coroutines for asynchronous programming, and JSON serialization for data handling.
This server is for fetching,adding,updating,deleting Characters of Boruto Series, it supports paggination.

## Technologies Used

- **IntelliJ IDEA**: The primary IDE for server-side Kotlin development.
- **Koin Dependency Injection**: A lightweight dependency injection framework for Kotlin.
- **Ktor Client**: An asynchronous networking framework for Kotlin and Android.
- **REST API**: Utilized for communication with clients and handling CRUD operations.
- **Mongo Database**: Used for persistent data storage.
- **Coroutines**: Kotlin's native approach to asynchronous programming.
- **Ktor Framework**: A Kotlin-based asynchronous web framework.
- **JSON Serialization**: For handling JSON data in the server.

## Screenshots

![Screenshot from 2025-05-18 12-06-52](https://github.com/user-attachments/assets/3e5144a7-9c1b-4244-8217-741b90fb755e)
####
![Screenshot from 2025-05-18 12-07-02](https://github.com/user-attachments/assets/258307b0-02c5-4cff-a619-cfe1c33f7c7d)
####
![Screenshot from 2025-05-18 12-18-31](https://github.com/user-attachments/assets/d8c3cd5b-bb23-4ef0-9dac-e849f25785a2)



## Setup

To run this server locally, make sure you have IntelliJ IDEA installed. Clone the repository and open it in IntelliJ IDEA. Set up a MongoDb compass and configure the connection settings in the application. Build and run the server application.

## API Endpoints

- `/addHeroes`: Add new Heroes to the database.
- `/boruto/heroes`: Retrieves all the heroes.
- `/boruto/heroes/search`: search hero.
- More

## Usage

[Provide instructions on how to interact with the API, including example requests and responses.]

## Contributing

Contributions are welcome! Please follow the [contribution guidelines](CONTRIBUTING.md) before submitting any pull requests.

## License

[Include your license information here. Example: This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.]

## Contact

[Include contact information or a way for users to reach out for support or questions.]
