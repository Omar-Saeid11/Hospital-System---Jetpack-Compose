# Hospital System App

**HospitalSystemApp** is an advanced healthcare management system developed using modern Android technologies like Jetpack Compose and Kotlin Coroutines. This app streamlines hospital operations by integrating various user roles, including HR, Manager, Receptionist, Doctor, Nurse, and Analysis staff, to enhance efficiency and communication within the healthcare environment.

## Key Features
- **Clean Architecture**: Utilizes Jetpack Compose and Kotlin Coroutines for a robust and maintainable codebase.
- **Seamless Navigation**: Implements the Navigation Controller with Jetpack Compose for intuitive user navigation.
- **Dependency Injection**: Uses **Dagger Hilt** to manage dependencies efficiently.
- **Engaging Animations**: Incorporates **Lottie animations** for an enhanced user experience through `android:lottie-compose`.
- **Network Operations**: Leverages **Retrofit** with **OkHttp3 Logging Interceptor** for detailed logging and network management.
- **Sleek Splash Screen**: Features a **Splash API** for a modern and appealing startup experience.
- **Security Features**: Supports **Biometric Authentication** via `androidx.biometric` for secure access.
- **Connectivity Monitoring**: Utilizes **ConnectivityObserver** and **ConnectivityStatus** to ensure reliable connectivity.
- **Secure Storage**: Implements **DataStore** for the secure storage of user preferences.

## User Roles and Operations
1. **HR**: Responsible for adding hospital employees by inputting relevant information.
2. **Manager**: Oversees all hospital cases, assigns tasks to staff, and manages reports effectively.
3. **Receptionist**: Facilitates communication between doctors and patients by creating calls with patient details.
4. **Doctor**: Responds to calls, monitors case details, and assigns tasks to nurses or the analysis team.
5. **Nurse**: Handles patient measurements and manages cases assigned by doctors.
6. **Analysis**: Reviews medical records and communicates results to doctors.

## Project Structure
The project adheres to clean architecture principles with well-defined layers:
- **core**: Contains utility classes, preferences, and network checks.
- **data**: Manages API interactions, data mappers, models, and repositories.
- **di**: Houses Dependency Injection modules.
- **domain**: Contains business logic and use cases.
- **presentation**: Encompasses UI components, view models, and connectivity management.
- **theme**: Handles application theming and styling.

## Tech Stack
- **Kotlin**
- **Jetpack Compose**
- **Coroutines**
- **Dagger Hilt** (Dependency Injection)
- **Retrofit** for API calls
- **OkHttp3 Logging Interceptor**
- **Lottie** for animations
- **Biometric Authentication**
- **DataStore** for secure user preferences storage

## How to Install and Run
1. Clone the repository:
   ```bash
   git clone https://github.com/Omar-Saeid11/HospitalSystemApp.git
