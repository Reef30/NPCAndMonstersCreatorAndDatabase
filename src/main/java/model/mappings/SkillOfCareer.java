package model.mappings;

import java.util.Objects;

public class SkillOfCareer {
    private int skillOfCareerId;
    private Integer careerId;
    private Integer careerLevel;
    private Integer skillId;
    private Integer specializationId;

    public int getSkillOfCareerId() {
        return skillOfCareerId;
    }

    public void setSkillOfCareerId(int skillOfCareerId) {
        this.skillOfCareerId = skillOfCareerId;
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

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillOfCareer that = (SkillOfCareer) o;
        return skillOfCareerId == that.skillOfCareerId &&
                Objects.equals(careerId, that.careerId) &&
                Objects.equals(careerLevel, that.careerLevel) &&
                Objects.equals(skillId, that.skillId) &&
                Objects.equals(specializationId, that.specializationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillOfCareerId, careerId, careerLevel, skillId, specializationId);
    }
}
