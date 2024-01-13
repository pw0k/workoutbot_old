## WorkoutBot - Daily Routine Video Checker

WorkoutBot is designed for groups that share daily workout videos and want to track progress.


### Key Features

- **Video Checking**: Members submit workout videos for accountability.
- **Video Hashing**: Utilizes Telegram's hashing mechanism for video  uniqueness.
- **Statistics**: Generate weekly/monthly stats to track progress.

WorkoutBot fosters a supportive fitness community for accountability and motivation.

### Environment Variables for Local Startup

To start the application locally, you need to set the following environment variables.
These can be added to your `.env` file or exported directly in your terminal:

```plaintext
WORKOUT_DATASOURCE_URL=your_database_url
WORKOUT_DB_USER=your_database_username
WORKOUT_DB_PASSWORD=your_database_password
TELEGRAM_TOKEN=your_telegram_bot_token
TELEGRAM_TEST_CHAT=TELEGRAM_TEST_CHAT
```

Replace jdbc:postgresql://db:5432/workoutdb, myuser, mypassword, and your_telegram_bot_token
with your actual PostgreSQL database details and Telegram bot token.

### Starting the Application Locally

To start your application locally using Docker Compose, follow these steps:

```bash
docker-compose up -d
```

   
This command starts the containers in detached mode. To view the logs, use `docker-compose logs -f`.