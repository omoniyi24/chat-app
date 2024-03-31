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
To use the chat application, set the environment variable `POSTMAN_COLLECTION_URL` to the URL of the Postman collection.

Follow the documented API endpoints to send, receive, and delete messages.
