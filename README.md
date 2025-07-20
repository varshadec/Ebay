# eBay Cart Automation Project

This project demonstrates automated testing of eBay's cart functionality using Selenium WebDriver with Java, following the Page Object Model (POM) design pattern and Cucumber BDD framework.

## 🚀 Features

- **Page Object Model**: Clean separation of page elements and test logic
- **Cucumber BDD**: Behavior-driven development with feature files
- **Selenium WebDriver**: Automated browser interaction
- **Maven**: Build and dependency management
- **Chrome WebDriver**: Automated browser testing

## 📋 Prerequisites

Before running this project, ensure you have the following installed:

- **Java JDK 11 or higher**
- **Maven 3.6 or higher**
- **Chrome Browser** (latest version)
- **Git** (for cloning the repository)

## 🛠️ Setup Instructions

### 1. Clone the Repository
```bash
git clone <your-repository-url>
cd EbayProject
```

### 2. Verify Java Installation
```bash
java -version
mvn -version
```

### 3. Run the Tests
```bash
# Run all tests
mvn test

# Run specific test runner
mvn test -Dtest=EbayCartTestRunner

# Run with detailed output
mvn test -Dtest=EbayCartTestRunner -X
```

## 📁 Project Structure

```
EbayProject/
├── src/
│   ├── main/java/org/example/
│   │   └── Main.java
│   └── test/
│       ├── java/org/example/
│       │   ├── EbayCartTestRunner.java
│       │   ├── pages/
│       │   │   └── EbayPage.java
│       │   ├── steps/
│       │   │   └── EbayCartSteps.java
│       │   └── utils/
│       │       └── TestUtils.java
│       └── resources/
│           ├── cucumber.properties
│           ├── features/
│           │   └── ebay_cart.feature
│           └── test-config.properties
├── pom.xml
└── README.md
```

## 🧪 Test Scenarios

### Current Test: "Verify the item in the cart"
1. Navigate to eBay website
2. Search for "book" in the search field
3. Click on the first book in search results
4. Add the book to cart
5. Verify cart count is updated (includes existing + new items)

## 🔧 Key Components

### Page Object (`EbayPage.java`)
- Encapsulates all eBay page elements using `@FindBy` annotations
- Contains methods for navigation, search, and cart operations
- Handles popup dialogs and window switching
- Implements cart count verification logic

### Step Definitions (`EbayCartSteps.java`)
- Maps Cucumber feature steps to page object methods
- Manages WebDriver lifecycle (setup/teardown)
- No assertions in step definitions (moved to page class)

### Test Utils (`TestUtils.java`)
- Utility methods for safe clicking and window switching
- Cart count extraction logic
- Page load waiting utilities

## 🎯 Design Patterns

- **Page Object Model**: XPath selectors defined as private WebElement fields
- **Clean Code**: No try-catch blocks or print statements in main logic
- **Separation of Concerns**: Assertions in page class, not step definitions
- **Explicit Waits**: WebDriverWait for reliable element interactions

## 📊 Test Results

The test validates:
- ✅ Successful navigation to eBay
- ✅ Book search functionality
- ✅ Product selection from search results
- ✅ Add to cart operation
- ✅ Cart count verification (maintains or increases count)

## 🔍 Troubleshooting

### Common Issues:

1. **ChromeDriver Version Mismatch**
   - Solution: The project uses WebDriverManager which automatically downloads the correct ChromeDriver version

2. **Element Not Found**
   - Check if eBay's page structure has changed
   - Verify XPath selectors in `EbayPage.java`

3. **Test Timeout**
   - Increase wait times in `EbayPage.java` if needed
   - Check internet connection stability

### Debug Mode:
```bash
# Run with debug output
mvn test -Dtest=EbayCartTestRunner -X
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📝 License

This project is for educational purposes. Please ensure compliance with eBay's terms of service when using this automation.

## 📞 Support

For issues or questions:
1. Check the troubleshooting section
2. Review the code comments
3. Create an issue in the repository

---

**Happy Testing! 🎉** # Ebay
