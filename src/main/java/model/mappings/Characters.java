package model.mappings;

import java.util.Objects;

public class Characters {
    private int characterId;
    private int raceId;
    private int careerId;
    private int careerLevel;
    private int statWs;
    private int statBs;
    private int statS;
    private int statT;
    private int statI;
    private int statAgi;
    private int statDex;
    private int statInt;
    private int statWp;
    private int statFel;
    private String characterName;

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getCareerId() {
        return careerId;
    }

    public void setCareerId(int careerId) {
        this.careerId = careerId;
    }

    public int getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(int careerLevel) {
        this.careerLevel = careerLevel;
    }

    public int getStatWs() {
        return statWs;
    }

    public void setStatWs(int statWs) {
        this.statWs = statWs;
    }

    public int getStatBs() {
        return statBs;
    }

    public void setStatBs(int statBs) {
        this.statBs = statBs;
    }

    public int getStatS() {
        return statS;
    }

    public void setStatS(int statS) {
        this.statS = statS;
    }

    public int getStatT() {
        return statT;
    }

    public void setStatT(int statT) {
        this.statT = statT;
    }

    public int getStatI() {
        return statI;
    }

    public void setStatI(int statI) {
        this.statI = statI;
    }

    public int getStatAgi() {
        return statAgi;
    }

    public void setStatAgi(int statAgi) {
        this.statAgi = statAgi;
    }

    public int getStatDex() {
        return statDex;
    }

    public void setStatDex(int statDex) {
        this.statDex = statDex;
    }

    public int getStatInt() {
        return statInt;
    }

    public void setStatInt(int statInt) {
        this.statInt = statInt;
    }

    public int getStatWp() {
        return statWp;
    }

    public void setStatWp(int statWp) {
        this.statWp = statWp;
    }

    public int getStatFel() {
        return statFel;
    }

    public void setStatFel(int statFel) {
        this.statFel = statFel;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characters that = (Characters) o;
        return characterId == that.characterId &&
                raceId == that.raceId &&
                careerId == that.careerId &&
                careerLevel == that.careerLevel &&
                statWs == that.statWs &&
                statBs == that.statBs &&
                statS == that.statS &&
                statT == that.statT &&
                statI == that.statI &&
                statAgi == that.statAgi &&
                statDex == that.statDex &&
                statInt == that.statInt &&
                statWp == that.statWp &&
                statFel == that.statFel &&
                Objects.equals(characterName, that.characterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(characterId, raceId, careerId, careerLevel, statWs, statBs, statS, statT, statI, statAgi, statDex, statInt, statWp, statFel, characterName);
    }
}
