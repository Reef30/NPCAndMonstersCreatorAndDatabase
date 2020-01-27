package model.mappings;

import java.util.Objects;

public class TalentsOfCharacter {
    private int talentOfCharacterId;
    private int characterId;
    private int talentId;
    private int timesTaken;

    public int getTalentOfCharacterId() {
        return talentOfCharacterId;
    }

    public void setTalentOfCharacterId(int talentOfCharacterId) {
        this.talentOfCharacterId = talentOfCharacterId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getTalentId() {
        return talentId;
    }

    public void setTalentId(int talentId) {
        this.talentId = talentId;
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
        TalentsOfCharacter that = (TalentsOfCharacter) o;
        return talentOfCharacterId == that.talentOfCharacterId &&
                characterId == that.characterId &&
                talentId == that.talentId &&
                timesTaken == that.timesTaken;
    }

    @Override
    public int hashCode() {
        return Objects.hash(talentOfCharacterId, characterId, talentId, timesTaken);
    }
}
