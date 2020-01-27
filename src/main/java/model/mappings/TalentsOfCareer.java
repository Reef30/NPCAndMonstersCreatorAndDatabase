package model.mappings;

import java.util.Objects;

public class TalentsOfCareer {
    private int talentOfCareerId;
    private Integer careerId;
    private Integer careerLevel;
    private Integer talentId;

    public int getTalentOfCareerId() {
        return talentOfCareerId;
    }

    public void setTalentOfCareerId(int talentOfCareerId) {
        this.talentOfCareerId = talentOfCareerId;
    }

    public Integer getCareerId() {
        return careerId;
    }

    public void setCareerId(Integer careerId) {
        this.careerId = careerId;
    }

    public Integer getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(Integer careerLevel) {
        this.careerLevel = careerLevel;
    }

    public Integer getTalentId() {
        return talentId;
    }

    public void setTalentId(Integer talentId) {
        this.talentId = talentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TalentsOfCareer that = (TalentsOfCareer) o;
        return talentOfCareerId == that.talentOfCareerId &&
                Objects.equals(careerId, that.careerId) &&
                Objects.equals(careerLevel, that.careerLevel) &&
                Objects.equals(talentId, that.talentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(talentOfCareerId, careerId, careerLevel, talentId);
    }
}
