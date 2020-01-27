package model.mappings;

import java.util.Objects;

public class Skills {
    private int skillId;
    private String skillName;
    private String description;
    private String baseStat;
    private int type;

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(String baseStat) {
        this.baseStat = baseStat;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skills skills = (Skills) o;
        return skillId == skills.skillId &&
                type == skills.type &&
                Objects.equals(skillName, skills.skillName) &&
                Objects.equals(description, skills.description) &&
                Objects.equals(baseStat, skills.baseStat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, skillName, description, baseStat, type);
    }
}
