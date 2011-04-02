package jbb.engine.pacman;

import android.app.Activity;
import android.os.Bundle;

public class PacmanActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        PacWorld board = new PacWorld();
        PacManView view = (PacManView) findViewById(R.id.pacman_view);
        view.setModel(board);
   }
}