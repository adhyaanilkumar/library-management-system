# How to Run the Application

## Quick Start (Using IDE - Recommended)

### IntelliJ IDEA:
1. Open IntelliJ IDEA
2. File → Open → Select the `library-management-system` folder
3. Wait for Maven to download dependencies (first time only)
4. Right-click on `LibraryManagementApplication.java`
5. Select "Run 'LibraryManagementApplication'"

### Eclipse:
1. Open Eclipse
2. File → Import → Maven → Existing Maven Projects
3. Select the `library-management-system` folder
4. Right-click on `LibraryManagementApplication.java`
5. Run As → Java Application

### VS Code:
1. Install "Extension Pack for Java" extension
2. Open the `library-management-system` folder
3. Open `LibraryManagementApplication.java`
4. Click "Run" button above the main method

## Using Command Line (Requires Maven)

### Install Maven:
1. Download from: https://maven.apache.org/download.cgi
2. Extract to a folder (e.g., `C:\Program Files\Apache\maven`)
3. Add to PATH:
   - Open System Properties → Environment Variables
   - Add `C:\Program Files\Apache\maven\bin` to PATH
4. Restart terminal and verify: `mvn -version`

### Run Application:
```bash
mvn spring-boot:run
```

## Access the Application

Once running, access at:
- **Home Page**: http://localhost:8080
- **View All Books**: http://localhost:8080/books
- **Add New Book**: http://localhost:8080/books/add
- **H2 Console**: http://localhost:8080/h2-console

