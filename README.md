# 🛒 Online Shopping Backend

This is a **Spring Boot e-commerce backend** for managing products, categories, orders, and users.  
It uses **JWT authentication** for secure access and **role-based authorization** for admin operations.

---

## 🛠️ Tech Stack

- **Backend:** Java, Spring Boot, Spring Web, Spring Data JPA  
- **Database:** MySQL 
- **Security:** Spring Security + JWT Authentication  
- **Build Tool:** Maven / Gradle  
- **Other:** Lombok

---

## 🧩 Features

### 🔐 Authentication & Authorization
- JWT-based authentication  
- Roles: **ADMIN**
- Only **ADMIN** can create/update/delete categories, subcategories, and child categories

### 🗂️ Categories
- `Category` → `SubCategory` → `ChildCategory` hierarchy  
- Admin-only operations for adding/editing/deleting  

### 🏷️ Products & Tags
- CRUD for products  
- Products can have multiple **tags** for better filtering/searching  
- Admin-only for product creation, update, delete

### 🛒 Orders
- Users can create orders with multiple **OrderItems**  
- Order management: create, view, update status  

---


---

## 🧠 Project Highlights

- **JWT Security:** Protects endpoints based on user roles  
- **Role-Based Access:** Admin-only actions are secured  
- **Hierarchical Categories:** Multi-level category management  
- **Products & Tags:** Flexible product tagging for search/filter  
- **Order Management:** Complete e-commerce order lifecycle  

---

## 🚀 How to Run

```bash
# Clone repository
git clone https://github.com/kaunghtetzaw139432/online-shopping.git

# Navigate into project folder
cd online-shopping

# Run backend (Spring Boot)
mvn spring-boot:run

# Access APIs at
http://localhost:8080
