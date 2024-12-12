# MAHA (My Assistant Health App)

MAHA is a digital platform designed to help individuals manage their healthy lifestyle through personalized health-related recommendations. The app offers diet advice and fitness routines tailored to the user's profile, empowering users to make informed decisions that support their health goals. By providing real-time guidance, MAHA encourages healthier lifestyles and helps users effectively manage their conditions.

## Features

- **Personalized Diet Recommendations**: Tailored diet plans based on user profiles, including dietary preferences and health conditions.
- **Fitness Routines**: Customized exercise recommendations to suit user fitness levels and goals.
- **Health Tracking**: Monitor progress and get insights based on tracked data.
- **Real-Time Guidance**: Immediate feedback and updates to support healthier lifestyle decisions.

## Target Market

MAHA is designed for individuals aged 18-40, especially:

- Young professionals
- Students
- Parents

These individuals are health-conscious yet time-constrained, with a strong interest in fitness, healthy lifestyles, and digital health solutions.

## Tech Stack

- **Frontend**: Kotlin (Android Studio)
- **Backend**: APIs with Node.js and Express
- **Database**: PostgreSQL
- **Machine Learning**: TensorFlow Lite for personalized health recommendations
- **Cloud Infrastructure**: Google Cloud Platform (GCP)

## Prerequisites

- Android Studio installed on your system
- Node.js and npm installed
- PostgreSQL database
- GCP account with $100 credits (if deploying to the cloud)

## Installation

### Backend Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/MAHA.git
   cd MAHA/backend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Configure environment variables by creating a `.env` file:
   ```
   DB_HOST=your-database-host
   DB_USER=your-database-user
   DB_PASS=your-database-password
   DB_NAME=your-database-name
   API_PORT=5000
   ```
4. Start the server:
   ```bash
   npm start
   ```

### Frontend Setup

1. Open the `frontend` folder in Android Studio.
2. Sync the project to download all dependencies.
3. Configure API endpoints in the `NetworkConfig` class:
   ```kotlin
   const val BASE_URL = "http://your-backend-api-url:5000"
   ```
4. Run the app on an emulator or physical device.

## How to Use

1. **Sign Up**: Create a new account and complete the health profile.
2. **Get Recommendations**: View personalized diet and exercise plans.
3. **Track Progress**: Use the tracker to monitor health goals and view analytics.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a feature branch: `git checkout -b feature-name`
3. Commit changes: `git commit -m 'Add new feature'`
4. Push to the branch: `git push origin feature-name`
5. Open a Pull Request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For questions or feedback:

- Email: support@mahaapp.com
- GitHub Issues: [Submit an issue](https://github.com/yourusername/MAHA/issues)
