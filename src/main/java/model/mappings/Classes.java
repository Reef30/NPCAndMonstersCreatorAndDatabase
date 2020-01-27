package model.mappings;

import java.util.Objects;

public class Classes {
    private int classId;
    private String className;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classes classes = (Classes) o;
        return classId == classes.classId &&
                Objects.equals(className, classes.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, className);
    }
}
