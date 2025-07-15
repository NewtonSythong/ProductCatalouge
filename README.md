# Product Catalogue Web Application

This is a university project for INFO202. It is a web application for browsing products, filtering by category, managing a shopping cart, and checking out. The backend is built with Jooby (Java), and the frontend uses Vue.js.

---

## Features

- Browse products and categories
- Filter products by category
- Add products to cart
- Checkout and submit sales
- User authentication (sign in/register)
- H2 database for data storage

---

## How to Run

### 1. **Start the H2 Database**

- On Windows, run `h2-windows.bat` (usually found in your H2 installation folder).

### 2. **Start the Application**

If you are using Gradle:
```sh
gradlew run
```
Or, if you use the Gradle tasks panel in VS Code:
- Open the Gradle sidebar
- Expand `application`
- Double-click the `run` task

### 3. **Access the Web App**

Open your browser and go to:
```
http://localhost:8087/static/index.html
```
or
```
http://localhost:8087/
```

---

## Project Structure

```
milestone2/
├── static/
│   ├── css/
│   ├── images/
│   ├── js/
│   └── *.html
├── src/
│   └── main/
│       └── java/
│           └── web/
│           └── dao/
│           └── ...
├── build.gradle
├── README.txt
└── ...
```

---

## Notes

- Make sure your H2 database is running before starting the application.
- Images should be placed in `static/images/` for correct display.
- If you change the database location or type, update your configuration accordingly.

---


## Troubleshooting

- If you see `404 Not Found` for images, check that they are in `static/images/`.
- If you see database connection errors, ensure H2 is running and your JDBC URL is correct.
- For authentication errors, make sure you are signed in before accessing protected endpoints.

---

## License

This project is for educational purposes.
