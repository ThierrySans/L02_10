
public class Student {
	
	private int studentNum = 0;
	private String studentName = "";
	
	/**
	 * Initialize the student with given information
	 * @param studentNum
	 * @param studentName
	 */
	public Student(int studentNum, String studentName) {
		this.studentNum = studentNum;
		this.studentName = studentName;
	}
	
	/**
	 * set the student number
	 * @param studentNum
	 */
	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}
	
	/**
	 * get the student number
	 * @return the number of student
	 */
	public int getStudentNum() {
		return this.studentNum;
	}
	
	/**
	 * set the student name
	 * @param studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName =  studentName;
	}
	
	/**
	 * get the student name
	 * @return the name of student
	 */
	public String getStudentName() {
		return this.studentName;
	}
	
}
















