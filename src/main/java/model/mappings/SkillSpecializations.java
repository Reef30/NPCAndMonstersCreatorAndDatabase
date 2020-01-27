package model.mappings;

import java.util.Objects;

public class SkillSpecializations {
    private int specializationId;
    private int skillId;
    private String specializationName;

    public int getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillSpecializations that = (SkillSpecializations) o;
        return specializationId == that.specializationId &&
                skillId == that.skillId &&
                Objects.equals(specializationName, that.specializationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specializationId, skillId, specializationName);
    }
}
