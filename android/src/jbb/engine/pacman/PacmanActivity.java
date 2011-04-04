package jbb.engine.pacman;

import jbb.engine.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PacmanActivity extends Activity {
	
	private TextView lives;
	private TextView points;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pacman);
        PacWorld board = new PacWorld();
        PacManView view = (PacManView) findViewById(R.id.pacman_view);
        view.setModel(board);
        lives = (TextView) findViewById(R.id.lives);
        points = (TextView) findViewById(R.id.points);
    }
    
    public void setLives(String text) {
    	lives.setText(text);
    }
    
    public void setPoints(String text) {
    	points.setText(text);
    }
}