package model.mappings;

import java.util.Objects;

public class Careers {
    private int careerId;
    private String careerName;
    private String level1;
    private String level2;
    private String level3;
    private String level4;
    private String stat1;
    private String stat2;
    private String stat3;
    private String stat4Bronze;
    private String stat5Silver;
    private String stat6Gold;
    private int classId;

    public int getCareerId() {
        return careerId;
    }

    public void setCareerId(int careerId) {
        this.careerId = careerId;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getLevel4() {
        return level4;
    }

    public void setLevel4(String level4) {
        this.level4 = level4;
    }

    public String getStat1() {
        return stat1;
    }

    public void setStat1(String stat1) {
        this.stat1 = stat1;
    }

    public String getStat2() {
        return stat2;
    }

    public void setStat2(String stat2) {
        this.stat2 = stat2;
    }

    public String getStat3() {
        return stat3;
    }

    public void setStat3(String stat3) {
        this.stat3 = stat3;
    }

    public String getStat4Bronze() {
        return stat4Bronze;
    }

    public void setStat4Bronze(String stat4Bronze) {
        this.stat4Bronze = stat4Bronze;
    }

    public String getStat5Silver() {
        return stat5Silver;
    }

    public void setStat5Silver(String stat5Silver) {
        this.stat5Silver = stat5Silver;
    }

    public String getStat6Gold() {
        return stat6Gold;
    }

    public void setStat6Gold(String stat6Gold) {
        this.stat6Gold = stat6Gold;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Careers careers = (Careers) o;
        return careerId == careers.careerId &&
                classId == careers.classId &&
                Objects.equals(careerName, careers.careerName) &&
                Objects.equals(level1, careers.level1) &&
                Objects.equals(level2, careers.level2) &&
                Objects.equals(level3, careers.level3) &&
                Objects.equals(level4, careers.level4) &&
                Objects.equals(stat1, careers.stat1) &&
                Objects.equals(stat2, careers.stat2) &&
                Objects.equals(stat3, careers.stat3) &&
                Objects.equals(stat4Bronze, careers.stat4Bronze) &&
                Objects.equals(stat5Silver, careers.stat5Silver) &&
                Objects.equals(stat6Gold, careers.stat6Gold);
    }

    @Override
    public int hashCode() {
        return Objects.hash(careerId, careerName, level1, level2, level3, level4, stat1, stat2, stat3, stat4Bronze, stat5Silver, stat6Gold, classId);
    }
}
