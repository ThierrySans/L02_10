import java.util.ArrayList;
import java.util.List;

public class Class {
	
	private String className = "";
	//a list of Student
	List<Student> students = new ArrayList<Student>();
	
	/**
	 * Initialize the class with given information
	 * @param className
	 * @param students
	 */
	public Class(String className, List<Student> students) {
		this.className = className;
		this.students = students;
	}
	
	/**
	 * set the name of class
	 * @param className
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
	/**
	 * get the name of class
	 * @return the name of class
	 */
	public String getClasdName() {
		return this.className;
	}
	
	/**
	 * set a list of Students
	 * @param students
	 */
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	/**
	 * get a list of Students of class
	 * @return list of students
	 */
	public List<Student> getStudents() {
		return this.students;
	}
}
