package by.piasecki.studentData.data;

public abstract class AbstractStudent {
    protected final String fullName;
    protected final String schoolName;
    protected final int schoolScore;
    protected final int averageMark;

    public AbstractStudent(String fullName
            , String schoolName
            , int schoolScore
            , int averageMark) {
        this.fullName = fullName;
        this.schoolName = schoolName;
        this.schoolScore = schoolScore;
        this.averageMark = averageMark;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public int getSchoolScore() {
        return schoolScore;
    }

    public int getAverageMark() {
        return averageMark;
    }

    public abstract String getType();
    public abstract int getScore();
}
