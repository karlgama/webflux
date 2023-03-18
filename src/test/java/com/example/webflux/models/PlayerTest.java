package com.example.webflux.models;

import com.example.webflux.repositories.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

class PlayerTest {
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void whenDeleteAll_then0IsExpected() {
        playerRepository.deleteAll()
                .as(StepVerifier::create)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    public void whenInsert6_then6AreExpected() {
        insertPlayers();
        playerRepository.findAll()
                .as(StepVerifier::create)
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    public void whenSearchForCR7_then1IsExpected() {
        insertPlayers();
        playerRepository.findAllByName("CR7")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void whenSearchFor32YearsOld_then2AreExpected() {
        insertPlayers();
        playerRepository.findByAge(32)
                .as(StepVerifier::create)
                .expectNextCount(2)
                .verifyComplete();
    }

    private void insertPlayers() {
        List<Player> players = Arrays.asList(
                new Player(1, "Kaka", 37),
                new Player(2, "Messi", 32),
                new Player(3, "Mbappé", 20),
                new Player(4, "CR7", 34),
                new Player(5, "Lewandowski", 30),
                new Player(6, "Cavani", 32)
        );
        playerRepository.saveAll(players).subscribe();
    }

}