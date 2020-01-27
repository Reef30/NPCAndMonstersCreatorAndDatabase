package model.mappings;

import java.util.Objects;

public class SkillsOfCharacter {
    private int skillsOfCharacterId;
    private int characterId;
    private int skillId;
    private Integer specializationId;
    private int timesTaken;

    public int getSkillsOfCharacterId() {
        return skillsOfCharacterId;
    }

    public void setSkillsOfCharacterId(int skillsOfCharacterId) {
        this.skillsOfCharacterId = skillsOfCharacterId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }

    public int getTimesTaken() {
        return timesTaken;
    }

    public void setTimesTaken(int timesTaken) {
        this.timesTaken = timesTaken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillsOfCharacter that = (SkillsOfCharacter) o;
        return skillsOfCharacterId == that.skillsOfCharacterId &&
                characterId == that.characterId &&
                skillId == that.skillId &&
                timesTaken == that.timesTaken &&
                Objects.equals(specializationId, that.specializationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillsOfCharacterId, characterId, skillId, specializationId, timesTaken);
    }
}
