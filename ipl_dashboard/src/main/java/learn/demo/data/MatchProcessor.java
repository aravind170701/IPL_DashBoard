package learn.demo.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;


import learn.demo.model.Match;

public class MatchProcessor implements ItemProcessor<MatchInput, Match>{

    private static final Logger log = LoggerFactory.getLogger(MatchProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) throws Exception {
    Match match = new Match();

    match.setId(Long.parseLong(matchInput.getId()));
    match.setCity(matchInput.getCity());
    match.setDate(LocalDate.parse(matchInput.getDate()));
    match.setPlayerOfMatch(matchInput.getPlayer_of_match());
    match.setVenue(matchInput.getVenue());
    match.setTeam1(matchInput.getTeam1());
    match.setTeam2(matchInput.getTeam2());
    match.setTossWinner(matchInput.getToss_winner());
    match.setTossDecision(matchInput.getToss_decision());

    String firstInningsTeam;
    String secondInningsTeam;

    if(matchInput.getToss_decision().equals("bat"))
    {
        firstInningsTeam = matchInput.getToss_winner();
        secondInningsTeam = matchInput.getTeam1().equals(matchInput.getToss_winner())
                ? matchInput.getTeam2() : matchInput.getTeam1();
    }
    else{
        secondInningsTeam = matchInput.getToss_winner();
        firstInningsTeam = matchInput.getTeam1().equals(matchInput.getToss_winner())
                ? matchInput.getTeam2() : matchInput.getTeam1();
    }
    
    match.setTeam1(firstInningsTeam);
    match.setTeam2(secondInningsTeam);

    match.setMatchWinner(matchInput.getWinner());
    match.setResult(matchInput.getResult());
    match.setResult_margin(matchInput.getResult_margin());
    match.setUmpire1(matchInput.getUmpire1());
    match.setUmpire2(matchInput.getUmpire2());

    return match;
  }
}

