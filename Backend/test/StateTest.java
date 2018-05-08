import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StateTest {

    @Test
    public void testEmptyConstructor() {
        State state = new State(2,2);
        assertTrue(state.toString().equals("2,2,0,0,0,2,0,0,2,2,0,0,0,2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,0,0,2,0,0,2,2,0"));
    }

    @Test
    public void testGetChildren() {
        int[] childrenTests = new int[]{12, 3, 3, 4, 5, 4, 4, 3, 3, 3, 2, 1};
        State state = new State(2,2);
        for (int i = 0; i < 12; i++) {
            List<State> children = state.getChildren();

            assertEquals(children.size(), childrenTests[i]);
            System.out.println(state);
            state = children.get(0);
        }
        System.out.println(state);
        assertEquals(state.score[0] + state.score[1], 4);

        List<State> children = state.getChildren();
        assertTrue(children.isEmpty());
    }

    @Test
    public void testGetChildren2() {
        State state = new State(5,5);
        for (int i = 0; i < 60; i++) {
            List<State> children = state.getChildren();
            System.out.println(state);
            state = children.get(0);
        }

        System.out.println(state);
        assertEquals(state.score[0] + state.score[1], 25);

        List<State> children = state.getChildren();
        assertTrue(children.isEmpty());
    }

    @Test
    public void testStringConstructor(){
        State state = new State("5,5,0,25,0,2,1,1,2,2,1,1,1,2,1,1,1,2,1,1,1,2,1,1,1,2,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,2,1,1,2,1,1,1,2,1,1,1,2,1,1,1,2,1,1,2,2,1");
        System.out.println(state);
        assertEquals(state.score[0] + state.score[1], 25);

        List<State> children = state.getChildren();
        assertTrue(children.isEmpty());
    }


}
