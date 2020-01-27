package model.mappings;

import java.util.Objects;

public class Talents {
    private int talentId;
    private String talentName;
    private String description;
    private String maxTaken;

    public int getTalentId() {
        return talentId;
    }

    public void setTalentId(int talentId) {
        this.talentId = talentId;
    }

    public String getTalentName() {
        return talentName;
    }

    public void setTalentName(String talentName) {
        this.talentName = talentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxTaken() {
        return maxTaken;
    }

    public void setMaxTaken(String maxTaken) {
        this.maxTaken = maxTaken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Talents talents = (Talents) o;
        return talentId == talents.talentId &&
                Objects.equals(talentName, talents.talentName) &&
                Objects.equals(description, talents.description) &&
                Objects.equals(maxTaken, talents.maxTaken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(talentId, talentName, description, maxTaken);
    }
}
