package model.mappings;

import java.util.Objects;

public class SkillOfRace {
    private int skillOfRaceId;
    private Integer raceId;
    private Integer skillId;
    private Integer specializationId;

    public int getSkillOfRaceId() {
        return skillOfRaceId;
    }

    public void setSkillOfRaceId(int skillOfRaceId) {
        this.skillOfRaceId = skillOfRaceId;
    }

    public Integer getRaceId() {
        return raceId;
    }

    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
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
        SkillOfRace that = (SkillOfRace) o;
        return skillOfRaceId == that.skillOfRaceId &&
                Objects.equals(raceId, that.raceId) &&
                Objects.equals(skillId, that.skillId) &&
                Objects.equals(specializationId, that.specializationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillOfRaceId, raceId, skillId, specializationId);
    }
}
