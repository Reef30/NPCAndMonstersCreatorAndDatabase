package model.mappings;

import java.util.Objects;

public class RaceStats {
    private int raceId;
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

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceStats raceStats = (RaceStats) o;
        return raceId == raceStats.raceId &&
                statWs == raceStats.statWs &&
                statBs == raceStats.statBs &&
                statS == raceStats.statS &&
                statT == raceStats.statT &&
                statI == raceStats.statI &&
                statAgi == raceStats.statAgi &&
                statDex == raceStats.statDex &&
                statInt == raceStats.statInt &&
                statWp == raceStats.statWp &&
                statFel == raceStats.statFel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(raceId, statWs, statBs, statS, statT, statI, statAgi, statDex, statInt, statWp, statFel);
    }
}
