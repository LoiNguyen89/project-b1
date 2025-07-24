package Entity;

public class CourseStats {
    private int courseId;
    private String courseName;
    private int studentCount;

    public CourseStats() {}
    public CourseStats(int courseId, String courseName, int studentCount) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentCount = studentCount;
    }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getStudentCount() { return studentCount; }
    public void setStudentCount(int studentCount) { this.studentCount = studentCount; }

    @Override
    public String toString() {
        return "CourseStats{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", studentCount=" + studentCount +
                '}';
    }
}
