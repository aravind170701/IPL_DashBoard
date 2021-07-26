package learn.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String teamName;
    private long totalMatches;
    private long matchesWon;

    @Transient
    private List<Match> matchesList;

    public List<Match> getMatchesList() {
        return matchesList;
    }
    public void setMatchesList(List<Match> matchesList) {
        this.matchesList = matchesList;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public long getTotalMatches() {
        return totalMatches;
    }
    public void setTotalMatches(long totalMatches) {
        this.totalMatches = totalMatches;
    }
    public long getMatchesWon() {
        return matchesWon;
    }
    public void setMatchesWon(long matchesWon) {
        this.matchesWon = matchesWon;
    }
    public Team(String teamName, long totalMatches) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }
    @Override
    public String toString() {
        return "Team [matchesWon=" + matchesWon + ", teamName=" + teamName + ", totalMatches=" + totalMatches + "]";
    }
    public Team() {
    }
    

}
