public class TerminalUI {

    public void Print(State state){

        String output = "";
        for (int cols = 0; cols < state.cols; cols++) {
            for (int rows = 0; rows < state.rows; rows++) {
                System.out.println("");
            }
        }

    }
}
