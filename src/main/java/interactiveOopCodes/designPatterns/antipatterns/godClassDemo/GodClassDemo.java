package interactiveOopCodes.designPatterns.antipatterns.godClassDemo;

/**
 * GodClassDemo - one class doing the work of many ("Blob" antipattern).
 *
 *   BAD: GodClassStudent holds the Student data AND knows how to import,
 *        export, validate, talk to the database, and probably make coffee.
 *        Every team (DB, IO, validation) edits the same class - merge
 *        conflicts and accidental breakage follow.
 *
 *   GOOD: A pure data class Student, plus three small interfaces, each
 *         with its own concrete implementation:
 *
 *            IImportExport         -> ImportExport
 *            IStudentDbOperations  -> StudentDbOperations
 *            IStudentValidation    -> StudentValidation
 *
 *   Cures: Single Responsibility Principle + Interface Segregation
 *   Principle. The diagram of this refactor is exactly the one shown
 *   on the "God Class Example" lecture slide.
 */
public class GodClassDemo {

    public static void main(String[] args) {
        // BAD: every concern goes through one class
        GodClassStudent god = new GodClassStudent();
        god.studentId   = "S00";
        god.studentName = "Bad Pattern";
        god.createNewStudent(god);
        god.exportStudentDetailsToCsv(god);

        // GOOD: each collaborator focuses on ONE concern
        Student              alex      = new Student("S01", "Alex", "SE-450", 3.8);
        IStudentValidation   validator = new StudentValidation();
        IStudentDbOperations db        = new StudentDbOperations();
        IImportExport        io        = new ImportExport();

        if (validator.validateModel(alex)) {
            db.createNewStudent(alex);
            io.exportToCsv(alex);
        }
        // Each interface can now be mocked, swapped, or evolved independently.
        System.out.println("OK: refactored design used " + alex);
    }
}

/* =====================  BAD: God Class  ===================== */

class GodClassStudent {
    String studentId;
    String studentName;
    String studentClass;
    double studentGpa;

    void getStudentDetails(String studentId)              { /* DB call */ }
    void createNewStudent(GodClassStudent s)              { System.out.println("[god] insert " + s.studentName); }
    boolean validateModel(GodClassStudent s)              { return s.studentName != null; }
    void exportStudentDetailsToCsv(GodClassStudent s)     { System.out.println("[god] export CSV " + s.studentName); }
    void importStudentDetailsToDatabase(GodClassStudent s){ /* IO + DB */ }
    // ...and more, growing forever
}

/* =====================  GOOD: SRP + ISP  ===================== */

/** Pure data - no behaviour. */
class Student {
    final String studentId, studentName, studentClass;
    final double studentGpa;

    Student(String id, String name, String klass, double gpa) {
        this.studentId    = id;
        this.studentName  = name;
        this.studentClass = klass;
        this.studentGpa   = gpa;
    }
    @Override public String toString() {
        return "%s %s (%s gpa=%.2f)".formatted(studentId, studentName, studentClass, studentGpa);
    }
}

interface IImportExport {
    void exportToCsv(Student s);
    void importFromDatabase(Student s);
}

class ImportExport implements IImportExport {
    @Override public void exportToCsv(Student s)       { System.out.println("[io] exporting " + s + " to CSV"); }
    @Override public void importFromDatabase(Student s){ System.out.println("[io] importing " + s + " from DB"); }
}

interface IStudentDbOperations {
    Student getStudentDetails(String studentId);
    void    createNewStudent(Student s);
}

class StudentDbOperations implements IStudentDbOperations {
    @Override public Student getStudentDetails(String studentId) {
        return new Student(studentId, "(loaded)", "CS", 3.5);
    }
    @Override public void createNewStudent(Student s) {
        System.out.println("[db] insert " + s);
    }
}

interface IStudentValidation {
    boolean validateModel(Student s);
}

class StudentValidation implements IStudentValidation {
    @Override public boolean validateModel(Student s) {
        return s.studentName != null && !s.studentName.isBlank() && s.studentGpa >= 0;
    }
}
