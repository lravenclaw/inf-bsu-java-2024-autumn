package by.piasecki.studentData.data;

public class UniStudent extends AbstractStudent {
    protected int  courseNumber;

    /*
     * почему я не вынес К1 и К2 в абстрактый класс?
     *
     * потому что это всего лишь заглушки и в реальном коде они могут иметь разное названия и
     * как следствие и назвачение
     */
    private final int K1 = 1;
    private final int K2 = 1;

    public UniStudent(String fullName
            , String schoolName
            , int schoolScore
            , int averageMark
            , int courseNumber) {
        super(fullName, schoolName, schoolScore, averageMark);
        this.courseNumber = courseNumber;
    }


    int getCourseNumber() {
        return courseNumber;
    }

    @Override
    public String getType() {
        return "University";
    }

    @Override
    public int getScore() {
        return K1 * schoolScore * averageMark + K2 * courseNumber;
    }
}
