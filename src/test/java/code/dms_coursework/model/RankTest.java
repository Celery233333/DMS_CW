package code.dms_coursework.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {
    Rank rank;

    @BeforeEach
    void reset() throws IOException {
        File file = new File("src\\main\\resources\\code\\dms_coursework\\highScore.txt");
        rank = new Rank(file);
        rank.initialize();
    }

    @Test
    void initialize() throws IOException {
        rank.initialize();
        for (int i=0;i<5;i++) {
            assertEquals(rank.getData()[i][0],"null");
            assertEquals(rank.getData()[i][1],"0");
        }
    }

    @Test
    void getData() {
        rank.modify("test",100,0);
        assertEquals(rank.getData()[0][0],"test");
        assertEquals(rank.getData()[0][1],"100");
    }

    @Test
    void check() {
        assertEquals(rank.check(100),0);
    }

    @Test
    void modify() {
        rank.modify("test",100,0);
        assertEquals(rank.getData()[0][0],"test");
        assertEquals(rank.getData()[0][1],"100");
    }
}