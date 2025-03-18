

# Selenium Automation Script for Registration, Login, Product Selection and Checkout

This program automates the process of registering a new user, logging in, browsing products, and completing a purchase on an e-commerce website. It uses random data to make the test more dynamic and checks for key functionalities like adding items to the cart and placing orders.

## Steps of the Program:

1. **Setup (`MySetup` method)**:
   - The `@BeforeTest` annotation ensures this method runs first.
   - This method opens the browser, navigates to the store's homepage, and maximizes the browser window.

2. **Login and Registration Navigation (`LoginAndRegist` method)**:
   - Navigates to the "Login or Register" page by clicking a link.
   - Then proceeds to the registration form.

3. **Registration (`Registration` method)**:
   - Fills out the registration form with random names, email, and address details.
   - Selects a country and state from dropdown menus.
   - Generates a random username and password, then submits the registration form.

4. **Login (`LoginTest` method)**:
   - Navigates to the login page and uses the credentials generated during registration to log in.

5. **Selecting Men's Section (`SelectMenSection` method)**:
   - Navigates to the men's section of the store.

6. **Selecting Random Main Product Category (`SelectRandomMainProductCate` method)**:
   - Selects a random product category from the list on the page.

7. **Selecting a Random Sub-Product (`GetRandomSubProduct` method)**:
   - Selects a random sub-product within the chosen category.

8. **Checking for Product Availability (`CheckProductAvailability` method)**:
   - Verifies product availability by checking if the "Add to Cart" button is enabled.
   - If not available, navigates back to the men's section and repeats the process until a product is found.

9. **Entering Random Quantity (`EnterRandomQuantity` method)**:
   - Generates a random quantity (between 2 and 10) for the selected product.
   - Inputs the quantity into the product's quantity field.

10. **Adding Product to The Cart (`AddToCart` method)**:
    - Adds the selected product with the selected quantity to the cart.

11. **Checkout and Confirm Order (`CheckoutAndConfirmOrder` method)**:
    - Clicks the checkout button and confirms the order to complete the purchase process.
