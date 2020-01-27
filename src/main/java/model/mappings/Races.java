package model.mappings;

import java.util.Objects;

public class Races {
    private int raceId;
    private String raceName;
    private String playable;

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getPlayable() {
        return playable;
    }

    public void setPlayable(String playable) {
        this.playable = playable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Races races = (Races) o;
        return raceId == races.raceId &&
                Objects.equals(raceName, races.raceName) &&
                Objects.equals(playable, races.playable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raceId, raceName, playable);
    }
}
