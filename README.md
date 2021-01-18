# 🏫 Sistem Manajemen Fakultas dan Jurusan
> Sistem dapat digunakan untuk merekam data pembuatan fakultas dan jurusan, termasuk waktu pembuatannya.
> Fakultas dan Jurusan dapat memiliki lebih dari satu entry.
Dibuat untuk memenuhi tugas final project mata kuliah Pemrograman Berorientasi Objek, 20/21 Ganjil, S1 Ilmu Komputer, Universitas Lampung. 

🧰 Library dan Alat dari proyek ini:
- mysql-connector-java-5.1.xx.jar
- Scene Builder
- MySQL server
- NetBeans

## ✏️ Desain
Untuk melihat diagram di bawah ini, instal plugin mermaid-diagram di https://github.com/Redisrupt/mermaid-diagrams

### Class Diagram
```mermaid
classDiagram
    department <|-- faculty
    
    class department{
      +int id
      +varchar name
      +created timestamp
      
    }
    
    class faculty{
      +int id
      +varchar name
      +created timestamp
    }
```

### ER Diagram
```mermaid
erDiagram
          department ||..|| faculty : is
         
          department {
            int id
            varchar name
            created timestamp
          }
          faculty{
             int id
            varchar name
            created timestamp
          }
```

### Desain Class Diagram untuk JavaFX dan Database
```mermaid
classDiagram
    MainController -->"1..1" Department : tableDepartment
    MainController -->"1..1" Department : colId
    MainController -->"0..1" Department : departments
    MainController -->"1..1" Department : colName
    MainController -->"0..*" Faculty : faculties
    MainController -->"1..1" Faculty : comboFaculty
    MainController -->"1..1" Faculty : tableFaculty
    MainController -->"1..1" Faculty : colFaculty
    MainController -->"1..1" Faculty : colFId
    MainController -->"1..1" Faculty : colFName
    MainController -->"1..1" DepartmentDaoImpl : departmentDao
    MainController -->"1..1" FacultyDaoImpl : FacultytDao
    DepartmentDaoImpl "1"..>"*" DaoService : <<implements>>
    FacultyDaoImpl "1"..>"*" DaoService : <<implements>>
    DepartmentDaoImpl "1..1"..>"1..1" Department : 
    FacultyDaoImpl "1"..>"*" Faculty : 
    Department -->"1..1" Faculty : faculty 
    Department "1..1"..>"1..1" Faculty : 
    DepartmentDaoImpl --> MySQLConnection : 
    FacultyDaoImpl --> MySQLConnection
    
    class Main{
    +void start(Stage pimaryStage)
    }

    class MySQL Connection{
    -final String URL
    -final String USERNAME
    -final String PASSWORD
    
    + static Connection create Connection()
    }

    class MainController{
      -TextField txtFacultyName
      -TextField txtDepartmentName
      -ComboBox<Faculty> comboFaculty
      -TableView<Faculty> tableFaculty
      -TableColumn<Faculty, Integer> colFId
      -TableColumn<Faculty, String> colFName
      -TableView<Department> tableDepartment
      -TableColumn<Department, Integer> colId
      -TableColumn<Department, String> colName
      -TableColumn<Department, Faculty> colFaculty
      -ObservableList<Faculty> faculties
      -ObservableList<Department> departments
      -FacultyDaolmpl facultyDao
      -DepartmentDaolmpl departmentDao

      -void saveFacultyAction(Action Event actionEvent)
      -void saveDepartmentAction(Action Event actionEvent)
      +void initialize(URL location, ResourceBundle resources)
    }
   
    class Department{
      -int id
      -String name
      -Faculty faculty

      +int getId()
      +void setId(int id)
      +String getName()
      +void setName(String name)
      +Faculty getFaculty()
      +void setFaculty(Faculty faculty)
    }

    class DepartmentDaoImpl{
    +List<Department> fetchAll()
    +int addData(Department object)
    }

    class Faculty{
    -int id
    -String Name

    +int getId()
    +void setId(int id)
    +String getName()
    +void setName(String Name)
    +String toString()
    }

    class FacultyDaoImpl{
    +List<Faculty> fetchAll()
    +int addData(Faculty object)
    }

    class DaoService{
    <<interface>>
    -List<T> fetchAll()
    -int addData(T object)
    }

    
```
