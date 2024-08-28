<h1>JAVAEE POS System Backend</h1>

<h3>API Documentation :</h3>
https://documenter.getpostman.com/view/36188605/2sAXjJ4sBa

<h3>JAVAEE POS System Frontend</h3>
https://github.com/SameeraMS/Pos-System-FrontEnd.git

<h3>Description</h3>

This project is built using Jakarta EE for the backend, MySQL as the database, and AJAX (or Fetch API) for handling asynchronous communication between the client and server. The application leverages native SQL for database operations to ensure efficient query handling. Proper logging is implemented across the application, following best practices for logging levels to ensure maintainability and traceability.

<h3>Tech Stack</h3>

<ul>
<li>Jakarta EE: Provides a robust enterprise platform for developing web applications.</li>
<li>MySQL: A widely used relational database management system.</li>
<li>AJAX: For asynchronous communication between the client and server.</li>
<li>JNDI: Used for database configuration to keep database connections manageable and secure.</li>
</ul>

<h3>Features</h3>

JNDI-based database configuration.
AJAX/Fetch API for dynamic content loading.
Comprehensive logging strategy with appropriate logging levels.

<h3>Setup & Installation</h3>

<ul>
<li>Clone the repository:
git clone https://github.com/SameeraMS/Pos-System-BackEnd.git</li>
<li>Configure the Database:
Set up your MySQL database.
<li>Update the JNDI configuration in your application server's context file to point to your MySQL database.</li>
<li>Build & Deploy:
Build the project using your preferred Jakarta EE tool (e.g., Maven).
Deploy the application to your Jakarta EE application server (e.g., Tomcat).</li>
<li>Access the Application:
Once deployed, access the frontend application via your web browser.
<br>
Frontend : <a>https://github.com/SameeraMS/Pos-System-FrontEnd.git</a>
</li>
</ul>

<h3>Logging Configuration</h3>

The application employs a robust logging mechanism with different logging levels:

<ul>
<li>INFO: General application flow.</li>
<li>DEBUG: Detailed debugging information.</li>
<li>ERROR: Error events of considerable importance that might still allow the application to continue running.</li>
<li>WARN: Potentially harmful situations.</li>
</ul>

