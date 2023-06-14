public abstract class Marks {
    protected int marks;

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public abstract void useMarks();
    public Marks createMarks(String type, int marks) {
        if (type.equals("Assignment")) {
            return new AssignmentMarks(marks);
        } else if (type.equals("Quiz")) {
            return new QuizMarks(marks);
        } else if (type.equals("Final")) {
            return new FinalMarks(marks);
        }
        return null; 
    }

   
}

class QuizMarks extends Marks {
    public QuizMarks(int quizMarks) {
        this.marks = quizMarks;
    }

    @Override
    public void useMarks() {
    }
}

class AssignmentMarks extends Marks {
    public AssignmentMarks(int assignmentMarks) {
        this.marks = assignmentMarks;
    }

    @Override
    public void useMarks() {
    }
}

class FinalMarks extends Marks {
    public FinalMarks(int finalMarks) {
        this.marks = finalMarks;
    }

    @Override
    public void useMarks() {
    }
}
class MarksHandlerFactory{
    private static MarksHandlerFactory instance  = null;
    private MarksHandlerFactory(){}
    public static synchronized MarksHandlerFactory getInstance(){
        if (instance == null)
        {
            instance = new MarksHandlerFactory();
        }
        return instance;
    }
    public Marks createMarks(String type, int marks) {
        if (type.equals("Assignment")) {
            return new AssignmentMarks(marks);
        } else if (type.equals("Quiz")) {
            return new QuizMarks(marks);
        } else if (type.equals("Final")) {
            return new FinalMarks(marks);
        }
        return null;
    }
}