package learn.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import learn.demo.model.Match;
import learn.demo.repository.MatchRepository;

@Service
public class MatchService {
    
    @Autowired
    private MatchRepository repo;

    public List<Match> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable)
    {
        return repo.findByTeam1OrTeam2OrderByDateDesc(team1, team2, pageable);
    }
}
