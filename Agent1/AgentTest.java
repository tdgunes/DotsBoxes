
import java.util.ArrayList;

public class AgentTest {

	public static void main(String[] args) {
		testTwoAgents();
	}
	
	static void testOneAgent(){
		RandomAgent rAgent = new RandomAgent();
		State state = new State(5, 5);
		ArrayList<State> states = new ArrayList<>();
		states.add(state);
		while(!state.isGameFinished()) {
			state = rAgent.generateNextState(state);
			states.add(state);
		}
		for (State pState : states){
			System.out.println(pState.toString());
		}
	}
	
	static void testTwoAgents(){
		RandomAgent rAgent1 = new RandomAgent();
		AvoidMakingBoxesAgent rAgent2 = new AvoidMakingBoxesAgent();
		State state = new State(5, 5);
		ArrayList<State> states = new ArrayList<>();
		states.add(state);
		while(!state.isGameFinished()) {
			if (state.turn == 0) {
				state = rAgent1.generateNextState(state);
			} else {
				state = rAgent2.generateNextState(state);
			}
			states.add(state);
		}
		for (State pState : states){
			System.out.println(pState.toString());
		}
		System.out.println("Score 0: " + state.score[0]);
		System.out.println("Score 1: " + state.score[1]);
	}
}
