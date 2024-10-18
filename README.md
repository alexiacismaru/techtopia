# Techtopia 

This project covers bounded contexts in programming by building the backend and front end of a ticketing system using React.

**Medium article**: https://medium.com/@alexia.csmr/using-bounded-contexts-to-build-a-java-application-1c7995038d30.

## About the project

The goal is to create a ticketing system for an amusement park called Techtopia. It's made out of the backend component using a hexagonal architecture, and the front end is created in React and TypeScript. The project has 3 main bounded contexts: Tickets, Attractions, and Entrance Gates. Each bounded context has its directory and includes components like in and our ports, adapters, use cases, etc.

Using KeyCloak ensures security. First, I created the Docker image of the Java project, then the realm and client. I used KeyCloak to log in to the website on the React app.

## Features
- **Hexagonal Architecture**: the application is designed around domain logic to isolate it from external factors.
- **Bounded Contexts**: a design pattern that helps with complexity, and collaboration and aligns the system with the problem domain.

## Installation
1. Clone the repo
   ```
   git clone https://github.com/alexiacismaru/techtopia.git
   ```
2. Install dependencies
   ```
   npm install
   npm run
   ```
3. Run the project
  - Frontend   
     ```
     npm run dev 
     ```
  - Backend
    ```
    ./gradlew run
    ```

## Technologies used
- **React**: library made for user interfaces. 
- **Storybook**: a development environment tool that is used as a playground for UI components
- **NPM**: a package manager for Node.js packages, or modules.  
- **Docker**: used to create the Docker images used for KeyCloak
- **KeyCloak**: used for security
- **Java**: used to build the backend

## License

Created by Alexia Cismaru.
