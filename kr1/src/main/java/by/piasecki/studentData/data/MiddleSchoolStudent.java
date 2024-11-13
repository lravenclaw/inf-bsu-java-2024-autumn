package by.piasecki.studentData.data;

public class MiddleSchoolStudent extends AbstractStudent {
    protected int gradeNumber;
    protected int behaviorScore;

    /*
    * почему я не вынес К1 и К2 в абстрактый класс?
    *
    * потому что это всего лишь заглушки и в реальном коде они могут иметь разное названия и
    * как следствие и назвачение
    */
    private final int K1 = 1;
    private final int K2 = 1;

    public MiddleSchoolStudent(String fullName
            , String schoolName
            , int schoolScore
            , int averageMark
            , int gradeNumber
            , int behaviorScore) {
        super(fullName, schoolName, schoolScore, averageMark);
        this.gradeNumber = gradeNumber;
        this.behaviorScore = behaviorScore;
    }

    public int getGradeNumber() {
        return gradeNumber;
    }

    public int getBehaviorScore() {
        return behaviorScore;
    }

    @Override
    public String getType() {
        return "Middle School";
    }

    @Override
    public int getScore() {
        return K1 * schoolScore * averageMark * gradeNumber + K2 * behaviorScore;
    }
}
