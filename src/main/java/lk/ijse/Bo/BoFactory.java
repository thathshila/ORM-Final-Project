package lk.ijse.Bo;

import lk.ijse.Bo.Custom.Impl.CourseBoImpl;
import lk.ijse.Bo.Custom.Impl.UserBoImpl;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBoFactory() {
        return boFactory == null ? boFactory = new BoFactory() : boFactory;
    }

    public enum BoType {
        USER,COURSE
    }

    public SuperBo getBoType(BoType boType) {
        switch (boType) {
            case USER:
                return new UserBoImpl();
            case COURSE:
                return new CourseBoImpl();
            default:
                return null;
        }
    }
}