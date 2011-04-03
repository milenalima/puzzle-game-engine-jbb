package jbb.engine.pipes;

import jbb.engine.R;
import android.app.Activity;
import android.os.Bundle;

public class PipesActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pipes);
        PipeMap board = new PipeMap();
        PipesView view = (PipesView) findViewById(R.id.pipes_view);
        view.setModel(board);
   } 
}