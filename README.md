# chat-app
a chat server system, enabling users to join chat rooms, send messages, and access chat history.

## Requirements

- Docker
- Docker Compose
- Java 17+
- Maven

## Getting Started

### Clone Repository
```bash
git clone https://github.com/omoniyi24/chat-app.git
cd chat-app
```

To get started with the chat application, follow these steps:

### 1. Build the Application

First, compile the application and create a Docker image:

```bash
mvn package
docker build -t chatapp .
```

### 2. Start Services with Docker Compose
Start Services with Docker Compose
Run the following command to start both the chat application and RabbitMQ:
```bash
docker-compose up
```
This command starts all services defined in docker-compose.yml. The chat application is accessible at http://localhost:8080, and RabbitMQ management interface is available at http://localhost:15672.

### 3. Using the Application
Now that everything is set up, you can start using the chat application.

Import the postman collection in the link below:

https://api.postman.com/collections/29543098-1fb7d4b9-2c44-461f-b849-0e66a5dc75c3?access_key=PMAT-01HTA3JETP2YNQW09J5BN5BBBX

Follow the documented API endpoints to send, receive and delete messages.