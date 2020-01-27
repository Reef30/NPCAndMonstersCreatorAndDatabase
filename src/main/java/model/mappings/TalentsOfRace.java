package model.mappings;

import java.util.Objects;

public class TalentsOfRace {
    private int talentOfRaceId;
    private Integer raceId;
    private Integer talentId;

    public int getTalentOfRaceId() {
        return talentOfRaceId;
    }

    public void setTalentOfRaceId(int talentOfRaceId) {
        this.talentOfRaceId = talentOfRaceId;
    }

    public Integer getRaceId() {
        return raceId;
    }

    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
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
        TalentsOfRace that = (TalentsOfRace) o;
        return talentOfRaceId == that.talentOfRaceId &&
                Objects.equals(raceId, that.raceId) &&
                Objects.equals(talentId, that.talentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(talentOfRaceId, raceId, talentId);
    }
}
