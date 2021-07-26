package learn.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import learn.demo.model.Team;
import learn.demo.repository.TeamRepository;

@Service
public class TeamService {
    
    @Autowired
    private TeamRepository repo;

    @Autowired
    private MatchService matchService;

    public TeamService() {
    }

    public TeamService(TeamRepository repo, MatchService matchService) {
        this.repo = repo;
        this.matchService = matchService;
    }

    public Team getByTeamName(String teamName)
    {
        Team team = repo.findByTeamName(teamName);

        Pageable pageable = PageRequest.of(0, 5);

        team.setMatchesList(matchService.findByTeam1OrTeam2OrderByDateDesc(teamName, teamName,pageable));

        return team;
    }

    
}
