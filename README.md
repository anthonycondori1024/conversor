# Currency Converter App

![Java](https://img.shields.io/badge/Java-25-red)
![Status](https://img.shields.io/badge/Status-%20Working-yellow)
![License](https://img.shields.io/badge/License-MIT-green)
![Gson](https://img.shields.io/badge/Gson-2.10+-85EA2D?logo=google&logoColor=white)
![API, AAAA](https://img.shields.io/badge/API-ExchangeRateAPI-blue)

> A high-performance, minimalist currency conversion tool built with Java 25 and Maven. This application leverages the
> ExchangeRate-API to provide real-time conversion data with a focus on clean architecture and immutable data models.

## 🛠 Features <a name="id1"></a>

- Real-time Rates: Fetches the most recent exchange rates for over 160+ currencies.
- Immutable Data: Built using Java Records for thread-safety and data integrity.
- Modern I/O: Uses java.net.http for asynchronous-ready requests and java.nio for efficient history logging.
- Minimalist UI: Designed for terminal-centric workflows.
---

## 🛠️ Technologies <a name="id2"></a>

| Technology           | Use                                                      |
|----------------------|----------------------------------------------------------|
| **Java**             | Main programming language                                |
| **Gson-2.13.2**      | Response parsing                                         | 
| **ExchangeRate-API** | Real-time data source                                    |

---

## 🚀 Getting Started <a name="id3"></a>

### Prerequisites <a name="id4"></a>

- JDK 25 or higher (Targeted for the latest Java features).
- Maven 3.9+
- An API Key from ExchangeRate-API.
 
---

### Installation & Setup <a name="id5"></a>

1. Clone the repository:

    ```bash
    git clone https://github.com/anthonycondori1024/conversor.git
    cd conversor
    ```

2. Configure your API Key:
   Navigate to the `resources` folder and create the configuration file.

    ```bash
    cp src/main/resources/config.properties.example src/main/resources/config.properties
    ```
   Open src/main/resources/config.properties and paste your API key:

   ```properties
   API_KEY=your_actual_key_here
   ```

3. Build the project:
    
   ```bash
   mvn clean compile
   ```
   
## Running the App <a name="id6"></a>
Execute the following command to start the interactive console:

   ```bash
   mvn exec:java -Dexec.mainClass="dev.anthony.ConverterApp"
   ```
## Architecture <a name="id7"></a>
The project follows a decoupled tiered architecture to ensure maintainability:

- `model`: Contains immutable Records (Currency, Conversion) representing the domain.
- `api`: : Handles HTTP communication and JSON parsing via Gson.
- `service`: Orchestrates the business logic and manages the state.
- `util`: Low-level file system operations using java.nio.
- `ui`: : The interactive CLI menu loop.

## Usage <a name="id8"></a>
Once the application is running, you can:

1. Convert: Enter source and target currency codes (e.g., USD, EUR) and the amount.
2. History: View all previous conversions stored in conversions.log.
3. List: See a complete list of supported currency codes currently available from the API.

## ⚖️ License <a name="id9"></a>
This project is open-source and available under the MIT License. [Click here](LICENSE) for more details.
